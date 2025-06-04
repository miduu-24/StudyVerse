package com.studyverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.studyverse.models")  
public class StudyverseApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyverseApplication.class, args);
    }
}
