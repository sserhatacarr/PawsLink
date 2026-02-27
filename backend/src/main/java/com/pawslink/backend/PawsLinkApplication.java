package com.pawslink.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PawsLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(PawsLinkApplication.class, args);
    }
}
