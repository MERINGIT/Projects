package org.example;

import java.net.InetAddress;

public class Rule {
    private String action;
    private String protocol;
    private InetAddress sourceIP;
    private InetAddress sourceMask;
    private InetAddress destIP;
    private InetAddress destMask;
    private int startPort = -1;
    private int endPort = -1;

    public Rule(String action, String protocol, InetAddress sourceIP, InetAddress sourceMask,
                InetAddress destIP, InetAddress destMask) {
        this.action = action;
        this.protocol = protocol;
        this.sourceIP = sourceIP;
        this.sourceMask = sourceMask;
        this.destIP = destIP;
        this.destMask = destMask;
    }

    public void setPortRange(int startPort, int endPort) {
        this.startPort = startPort;
        this.endPort = endPort;
    }

    public String getAction() {
        return action;
    }

    public String getActionedMessage() {
        return action.equals("deny") ? "denied" : "permitted";
    }

    public boolean matches(InetAddress source, InetAddress dest, int port) {
        if (startPort != -1 && endPort != -1) {
            // Check for port range if specified
            return port == startPort || port == endPort;
        }

        if (sourceIP != null && !isInNetwork(source, sourceIP, sourceMask)) {
            return false;
        }

        if (destIP != null && !isInNetwork(dest, destIP, destMask)) {
            return false;
        }

        return true;
    }

    private static boolean isInNetwork(InetAddress ipAddress, InetAddress networkAddress, InetAddress subnetMask) {
        byte[] ipBytes = ipAddress.getAddress();
        byte[] networkBytes = networkAddress.getAddress();
        byte[] subnetBytes = subnetMask.getAddress();

        for (int i = 0; i < ipBytes.length; i++) {
            int subnetMaskValue = subnetBytes[i] & 0xFF;

            if (subnetMaskValue == 0) {
                if (ipBytes[i] != networkBytes[i]) {
                    return false;
                }

            } else if (subnetMaskValue == 255) {
                if (ipBytes[i] < 0 || ipBytes[i] > 255) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "action='" + action + '\'' +
                ", protocol='" + protocol + '\'' +
                ", sourceIP=" + sourceIP +
                ", sourceMask=" + sourceMask +
                ", destIP=" + destIP +
                ", destMask=" + destMask +
                ", startPort=" + startPort +
                ", endPort=" + endPort +
                '}';
    }
}
