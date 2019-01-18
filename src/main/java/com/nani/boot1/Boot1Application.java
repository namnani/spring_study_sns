package com.nani.boot1;

import com.nani.boot1.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot1Application {

    public static void main(String[] args) {

        User user = new User();

        SpringApplication.run(Boot1Application.class, args);
    }

}

