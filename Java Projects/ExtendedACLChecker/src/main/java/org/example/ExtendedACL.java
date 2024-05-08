package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

public class ExtendedACL {

    public static void main(String[] args) {
        String aclFile = "acl_rules.txt";
        String inputFile = "input_addresses.txt";

        // List to store deny and permit rules
        List<Rule> aclRules = new LinkedList<>();

        // Read ACL statements from file 1
        readACLFile(aclFile, aclRules);

        // Read input addresses and port numbers from file 2
        readAndCheckInputFile(inputFile, aclRules);
    }

    private static void readACLFile(String aclFile, List<Rule> aclRules) {
        try (BufferedReader br = new BufferedReader(new FileReader(aclFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseACLStatement(line, aclRules);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseACLStatement(String line, List<Rule> aclRules) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length > 4) {
            String action = parts[2].toLowerCase();
            if (action.equals("deny") || action.equals("permit")) {
                if (parts.length > 4 && parts[0].equalsIgnoreCase("access-list") && parts[4].equalsIgnoreCase("any") && parts[5].equalsIgnoreCase("any")) {
                    // Handle "permit ip any any" rule
                    aclRules.add(new Rule("permit", "ip", null, null, null, null));
                } else {
                    String protocol = parts[3].toLowerCase();
                    InetAddress sourceIP = parseIPAddress(parts[4]);
                    InetAddress sourceMask = parseIPAddress(parts[5]);
                    InetAddress destIP = parseIPAddress(parts[6]);
                    InetAddress destMask = parseIPAddress(parts[7]);

                    Rule rule = new Rule(action, protocol, sourceIP, sourceMask, destIP, destMask);

                    if (parts.length == 10 && parts[8].equalsIgnoreCase("range")) {
                        // Additional handling for range specification
                        String[] range = parts[9].split("-");
                        if (range.length == 2) {
                            int startPort = Integer.parseInt(range[0]);
                            int endPort = Integer.parseInt(range[1]);
                            rule.setPortRange(startPort, endPort);
                        }
                    }

                    aclRules.add(rule);
                }
            }
        }
    }

    private static InetAddress parseIPAddress(String ipAddress) {
        try {
            return "any".equalsIgnoreCase(ipAddress) ? InetAddress.getByName("0.0.0.0") : InetAddress.getByName(ipAddress);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing IP address: " + ipAddress, e);
        }
    }

    private static void readAndCheckInputFile(String inputFile, List<Rule> aclRules) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                checkPacket(line.trim(), aclRules);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkPacket(String packetInfo, List<Rule> aclRules) {
        String[] packetDetails = packetInfo.split("\\s+");
        if (packetDetails.length == 3) {
            InetAddress sourceIP = parseIPAddress(packetDetails[0]);
            InetAddress destIP = parseIPAddress(packetDetails[1]);
            int port = Integer.parseInt(packetDetails[2]);

            boolean matched = false;

            for (Rule rule : aclRules) {
                if (rule.matches(sourceIP, destIP, port)) {
                    System.out.println("Packet from " + sourceIP.getHostAddress() + " to " +
                            destIP.getHostAddress() + " on port " + port + " " + rule.getActionedMessage());
                    matched = true;
                    break; // Exit the loop once a match is found
                }
            }

            if (!matched) {
                System.out.println("Packet from " + sourceIP.getHostAddress() + " to " +
                        destIP.getHostAddress() + " on port " + port + " denied"); // Default deny if no match found
            }
        }
    }

}
