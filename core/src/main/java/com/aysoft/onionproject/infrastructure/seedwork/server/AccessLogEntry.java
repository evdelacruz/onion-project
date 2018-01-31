package com.aysoft.onionproject.infrastructure.seedwork.server;

public class AccessLogEntry {
    private String pattern;
    private String prefix;
    private String suffix;

    public AccessLogEntry() {}

    //<editor-fold desc="Encapsulation">
    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    //</editor-fold>
}
