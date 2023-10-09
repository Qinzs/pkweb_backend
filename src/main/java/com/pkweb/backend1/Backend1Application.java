package com.pkweb.backend1;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EntityScan(basePackages = "com.pkweb.backend1.*")
public class Backend1Application {

    public static void main(String[] args) {
        SpringApplication.run(Backend1Application.class, args);
    }

}
