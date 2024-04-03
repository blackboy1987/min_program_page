package com.bootx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author black
 */
@SpringBootApplication
@EnableScheduling
public class MinProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinProgramApplication.class, args);
    }

}
