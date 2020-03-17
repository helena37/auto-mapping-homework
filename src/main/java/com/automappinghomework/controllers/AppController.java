package com.automappinghomework.controllers;

import com.automappinghomework.domain.models.dtos.*;
import com.automappinghomework.domain.models.views.GameSingleTitleDetailsViewDto;
import com.automappinghomework.services.GameService;
import com.automappinghomework.services.UserService;
import com.automappinghomework.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AppController implements CommandLineRunner {
    private final BufferedReader reader;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader reader, ValidationUtil validationUtil, UserService userService, GameService gameService) {
        this.reader = reader;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("!!!If You want to exit the program ENTER --> END <--");
            System.out.println("Please, enter command: ");

            String[] input = this.reader.readLine().split("\\|");

            if(input[0].equalsIgnoreCase("END")) {
                return;
            }

            switch (input[0]) {
                case "RegisterUser":
                    if (!input[2].equals(input[3])) {
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
                case "AddGame":
                    try {
                        GameAddDto gameAddDto = new GameAddDto(
                                input[1], new BigDecimal(input[2]), Double.parseDouble(input[3]),
                                input[4], input[5], input[6],
                                LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                        if (this.validationUtil.isValid(gameAddDto)) {
                            this.gameService.addGame(gameAddDto);
                            System.out.println("Added " + gameAddDto.getTitle());
                        } else {
                            this.validationUtil
                                    .getViolations(gameAddDto)
                                    .stream()
                                    .map(ConstraintViolation::getMessage)
                                    .forEach(System.out::println);
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("No logged in user!!! PLEASE, log in to make changes!!!");
                    }
                    break;
                case "EditGame":
                    try {
                        GameEditDto gameEditDto = this.gameService.findOneById(Long.parseLong(input[1]));

                        if (gameEditDto == null) {
                            System.out.printf("Game with Id %s doesn't exist!!!\r\n", input[1]);
                            return;
                        }

                        gameEditDto.setId(Long.parseLong(input[1]));

                        for (int i = 2; i < input.length; i++) {
                            String[] tokens = input[i].split("=");
                            switch (tokens[0]) {
                                case "title":
                                    gameEditDto.setTitle(tokens[1]);
                                    break;
                                case "price":
                                    gameEditDto.setPrice(new BigDecimal(tokens[1]));
                                    break;
                                case "size":
                                    gameEditDto.setSize(Double.parseDouble(tokens[1]));
                                    break;
                                case "thumbnail":
                                    gameEditDto.setImage(tokens[1]);
                                    break;
                                case "trailer":
                                    gameEditDto.setTrailer(tokens[1]);
                                    break;
                                case "description":
                                    gameEditDto.setDescription(tokens[1]);
                                    break;
                                case "date":
                                    gameEditDto.setReleaseDate(LocalDate.parse(tokens[1],
                                            DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                                    break;
                            }

                            if(this.validationUtil.isValid(gameEditDto)) {
                                this.gameService.editGame(gameEditDto);
                            } else {
                                this.validationUtil
                                        .getViolations(gameEditDto)
                                        .stream()
                                        .map(ConstraintViolation::getMessage)
                                        .forEach(System.out::println);
                            }
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("No logged in user!!! PLEASE, log in to make changes!!!");
                    }
                    break;
                case "DeleteGame":
                    GameDeleteDto gameDeleteDto = this.gameService.findById(Long.parseLong(input[1]));
                    this.gameService.deleteGame(gameDeleteDto);
                    break;
                case "AllGames":
                    this.gameService
                            .getAllGameTitlesAndPrices()
                            .forEach(game -> System.out.println(game.getTitle() + " " + game.getPrice()));
                    break;
                case "DetailGame":
                    GameSingleTitleDetailsViewDto gameSingleTitleDetails =
                            this.gameService.getSingleTitleDetails(input[1]);

                    String date = gameSingleTitleDetails.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    System.out.println(
                            String.format(
                                    "Title: %s\r\n" +
                                            "Price: %.2f\r\n" +
                                            "Description: %s\r\n" +
                                            "Release date: %s\r\n",
                                    gameSingleTitleDetails.getTitle(),
                                    gameSingleTitleDetails.getPrice(),
                                    gameSingleTitleDetails.getDescription(),
                                    date
                            )
                    );
                    break;
            }
        }
    }
}
