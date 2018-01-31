package com.aysoft.onionproject.infrastructure.seedwork.context;

public class SystemContext {
    private static final String CONF;
    private static final Package PACKAGE;

    static {
        try {
            PACKAGE = Class.forName("com.aysoft.onionproject.Main").getPackage();
            CONF = System.getProperty("com.aysoft.onionproject.conf");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Failed to start the application !!!", ex);
        }
    }

    static public String version() {
        return null == PACKAGE.getSpecificationVersion() ? "0.0.0" : PACKAGE.getImplementationVersion();
    }

    static public String conf() {
        return CONF;
    }

    private SystemContext() {
        throw new AssertionError("No 'SystemContext' instances for you!");
    }
}
