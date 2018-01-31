package com.aysoft.onionproject.infrastructure.seedwork.context;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static com.aysoft.onionproject.infrastructure.seedwork.context.SystemContext.conf;
import static java.lang.String.format;
import static java.util.Arrays.stream;

public class Runner {
    private static final String[] CONFIG_FILES = new String[]{"%s/application-config.yml", "%s/application-logback.xml", "%s/application-valves.json"};
    private Class<?> clazz;

    private Runner(Class<?> clazz) {
        this.clazz = clazz;
    }

    static public ApplicationContext run(Class<?> clazz, String[] args) {
        return new Runner(clazz).run();
    }

    //<editor-fold desc="Support methods">
    private ApplicationContext run() {
        if (!this.checkConfig()) {
            this.copy();
            throw new RuntimeException("Configuration not found !!!");
        }
        return new SpringApplication(clazz).run(this.args());
    }

    private String[] args() {
        return new String[] {"--spring.config.location="+format(CONFIG_FILES[0], conf()), "--logging.config="+format(CONFIG_FILES[1], conf())};
    }

    private boolean checkConfig() {
        return stream(CONFIG_FILES).map(format -> Paths.get(format(format, conf()))).allMatch(Files::exists);
    }

    private void copy() {
        File dir = new File(conf());
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("Config directory is inaccessible !!!");
        }
        stream(CONFIG_FILES).forEach(this::copy);
    }

    private void copy(String line) {
        try {
            StringBuilder pkg = new StringBuilder("/").append(clazz.getPackage().getName().replace(".", "/")).append("/configuration/templates");
            Files.copy(clazz.getResourceAsStream(format(line, pkg)), Paths.get(format(line, conf())), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException("An error has ocurred when trying to copy config templates !!!", ex);
        }
    }
    //</editor-fold>
}
