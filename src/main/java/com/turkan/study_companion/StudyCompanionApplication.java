package com.turkan.study_companion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.turkan.study_companion", "controller"})
@EntityScan(basePackages = "model")
@EnableJpaRepositories(basePackages = "repository")
public class StudyCompanionApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyCompanionApplication.class, args);
    }
}