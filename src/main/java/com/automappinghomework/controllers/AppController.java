package com.automappinghomework.controllers;

import com.automappinghomework.domain.models.UserLoginDto;
import com.automappinghomework.domain.models.UserRegisterDto;
import com.automappinghomework.services.UserService;
import com.automappinghomework.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;

@Component
public class AppController implements CommandLineRunner {
    private final BufferedReader reader;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    @Autowired
    public AppController(BufferedReader reader, ValidationUtil validationUtil, UserService userService) {
        this.reader = reader;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Please, enter command: ");
            String[] input = this.reader.readLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser":
                    if(!input[2].equals(input[3])) {
                        System.out.println("Password don't match!");
                        break;
                    }

                    UserRegisterDto userRegisterDto = new UserRegisterDto(input[1], input[2], input[4]);
                    if (this.validationUtil.isValid(userRegisterDto)) {
                        this.userService
                                .registerUser(userRegisterDto);
                        System.out.println(
                                String.format(
                                "%s was registered",
                                        input[4]
                        ));
                    } else {
                        this.validationUtil
                                .getViolations(userRegisterDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                    break;
                case "LoginUser":
                    UserLoginDto userLoginDto = new UserLoginDto(input[1], input[2]);
                    this.userService.loginUser(userLoginDto);
                    break;
                case "Logout":
                    this.userService.logoutUser();
                    break;
            }
        }
    }
}
