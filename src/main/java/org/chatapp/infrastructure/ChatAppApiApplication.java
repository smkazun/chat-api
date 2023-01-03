package org.chatapp.infrastructure;

import org.chatapp.infrastructure.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class ChatAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatAppApiApplication.class, args);
    }
}