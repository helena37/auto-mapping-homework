package com.automappinghomework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class AppController implements CommandLineRunner {
    private final BufferedReader reader;

    @Autowired
    public AppController(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String[] input = this.reader.readLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser":

                    break;
                case "LoginUser":
                    break;
                case "Logout":
                    break;
            }
        }
    }
}
