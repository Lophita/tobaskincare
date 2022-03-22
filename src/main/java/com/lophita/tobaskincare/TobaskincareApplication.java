package com.lophita.tobaskincare;

import com.lophita.tobaskincare.configuration.KafkaSenderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TobaskincareApplication {

    @Autowired KafkaSenderExample kafkaSenderExample;

    public static void main(String[] args) {
        SpringApplication.run(TobaskincareApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> kafkaSenderExample.sendMessage("topic1", "testKafkaSenderExample");
    }

}
