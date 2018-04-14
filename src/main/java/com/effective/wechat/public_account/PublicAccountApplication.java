package com.effective.wechat.public_account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublicAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicAccountApplication.class, args);
        System.out.println("====start weiChat public account server success====");
    }
}
