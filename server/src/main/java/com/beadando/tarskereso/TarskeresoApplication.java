package com.beadando.tarskereso;

import com.beadando.tarskereso.model.User;
import com.beadando.tarskereso.services.Tarskereso_services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

import java.util.List;

@SpringBootApplication
public class TarskeresoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TarskeresoApplication.class, args);

        Tarskereso_services service = new Tarskereso_services();
        List<User> test = service.fromFile("server/src/main/resources/Tarskereso_db.csv");
        System.out.println(test.get(0).getNev());

    }



}
