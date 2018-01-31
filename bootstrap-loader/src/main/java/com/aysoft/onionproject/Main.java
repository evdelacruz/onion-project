package com.aysoft.onionproject;

import com.aysoft.onionproject.infrastructure.seedwork.context.Runner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= "com.aysoft.onionproject.infrastructure.configuration")
public class Main {

    public static void main(String[] args) {
        Runner.run(Main.class, args);
    }
}
