package br.com.robertoxavier;

import br.com.robertoxavier.api.config.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        SpringApplication.run(WebServerConfig.class, args);
    }
}