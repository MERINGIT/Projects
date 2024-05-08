package org.example;

import java.net.InetAddress;

public  class Rule {
    private String action;
    private InetAddress sourceIP;
    private InetAddress sourceMask;

    public Rule(String action, InetAddress sourceIP, InetAddress sourceMask) {
        this.action = action;
        this.sourceIP = sourceIP;
        this.sourceMask = sourceMask;
    }

    public String getAction() {
        return action;
    }

    public InetAddress getSourceIP() {
        return sourceIP;
    }

    public InetAddress getSourceMask() {
        return sourceMask;
    }
}
