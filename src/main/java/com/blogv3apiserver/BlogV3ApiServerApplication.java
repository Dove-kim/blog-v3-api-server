package com.blogv3apiserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Slf4j
@EnableJpaRepositories
public class BlogV3ApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogV3ApiServerApplication.class, args);
    }

}
