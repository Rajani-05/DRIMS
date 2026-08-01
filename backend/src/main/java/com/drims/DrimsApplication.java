package com.drims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrimsApplication.class, args);
        System.out.println("\n=======================================================");
        System.out.println("  DRIMS - Data Research Information Management System  ");
        System.out.println("  Backend Service Running on http://localhost:8080/api ");
        System.out.println("=======================================================\n");
    }
}
