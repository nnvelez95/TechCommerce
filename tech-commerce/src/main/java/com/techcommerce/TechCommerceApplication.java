package com.techcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechCommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TechCommerceApplication.class, args);
        System.out.println("🚀 TechCommerce iniciado correctamente!");
    }
}