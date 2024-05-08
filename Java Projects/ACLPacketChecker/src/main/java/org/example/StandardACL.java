package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StandardACL {

    public static void main(String[] args) {
        String aclFile = "acl_rules.txt";
        String inputFile = "input_addresses.txt";

        // List to store all rules
        List<Rule> ruleList = new ArrayList<>();

        // Read ACL statements from file
        readACLFile(aclFile, ruleList);

        // Read input addresses from file and check packets
        readAndCheckInputFile(inputFile, ruleList);
    }

    private static void readACLFile(String aclFile, List<Rule> ruleList) {
        try (BufferedReader br = new BufferedReader(new FileReader(aclFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseACLStatement(line, ruleList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseACLStatement(String line, List<Rule> ruleList) {
        String[] parts = line.trim().split("\\s+");

        if (parts.length >= 4 && parts[0].equals("access-list")) {
            String action = parts[2].toLowerCase();
            InetAddress sourceIP = null;
            InetAddress sourceMask = null;

            if (parts[3].equalsIgnoreCase("any")) {
                // Null indicates "any" IP
                sourceIP = null;
                // Null indicates "any" subnet mask
                sourceMask = null;
            } else {
                sourceIP = parseIPAddress(parts[3]);
                sourceMask = parseIPAddress(parts[4]);
            }

            ruleList.add(new Rule(action, sourceIP, sourceMask));
        }
    }

    private static InetAddress parseIPAddress(String ipAddress) {
        try {
            return InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Error parsing IP address: " + ipAddress, e);
        }
    }

    private static void readAndCheckInputFile(String inputFile, List<Rule> ruleList) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                checkPacket(line.trim(), ruleList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkPacket(String packetIP, List<Rule> ruleList) {
        try {
            // Validate input IP syntax
            if (!isValidIpAddressSyntax(packetIP)) {
                System.out.println("Invalid IP syntax: " + packetIP);
                return;
            }

            InetAddress packetAddress = InetAddress.getByName(packetIP);

            for (Rule rule : ruleList) {
                if (isInNetwork(packetAddress, rule.getSourceIP(), rule.getSourceMask())) {
                    if (rule.getAction().equals("deny")) {
                        System.out.println("Packet from " + packetIP + " denied");
                    } else {
                        System.out.println("Packet from " + packetIP + " permitted");
                    }
                    return;
                }
            }

            System.out.println("Packet from " + packetIP + " denied"); // Default deny if no match found

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidIpAddressSyntax(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        if (octets.length != 4) {
            return false; // IP address should consist of four octets
        }

        for (String octet : octets) {
            try {
                int value = Integer.parseInt(octet);
                if (value < 0 || value > 255) {
                    return false; // Each octet should be within the range 0 to 255
                }
            } catch (NumberFormatException e) {
                return false; // Octet should be a valid integer
            }
        }

        return true;
    }

    private static boolean isInNetwork(InetAddress ipAddress, InetAddress networkAddress, InetAddress subnetMask) {
        if (networkAddress == null || subnetMask == null) {
            return true; // "Any" network, allow all packets
        }

        byte[] ipBytes = ipAddress.getAddress();
        byte[] networkBytes = networkAddress.getAddress();
        byte[] subnetBytes = subnetMask.getAddress();

        for (int i = 0; i < ipBytes.length; i++) {
            int subnetMaskValue = subnetBytes[i] & 0xFF;

            if (subnetMaskValue == 0) {
                // If subnet mask is 0, check for an exact match
                if (ipBytes[i] != networkBytes[i]) {
                    return false;
                }

            } else if (subnetMaskValue == 255) {
                // If subnet mask is 255, check if ipAddress value is between 0 to 255
                if (ipBytes[i] < 0 || ipBytes[i] > 255) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }


}
