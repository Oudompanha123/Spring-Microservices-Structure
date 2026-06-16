package org.example.sreviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SrevicebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrevicebApplication.class, args);
    }

}
