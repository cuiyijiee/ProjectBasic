package me.cuiyijie.projectbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ProjectBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBasicApplication.class, args);
    }

}