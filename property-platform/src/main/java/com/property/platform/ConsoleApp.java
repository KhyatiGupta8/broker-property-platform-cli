package com.property.platform;

import com.property.platform.console.ConsoleController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsoleApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }
    @Bean
    public CommandLineRunner runner(ConsoleController controller) {
        return args -> controller.start();
    }
}