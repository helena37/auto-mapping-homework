package com.automappinghomework.services;

import com.automappinghomework.domain.entities.Game;
import com.automappinghomework.domain.models.GameAddDto;
import com.automappinghomework.domain.models.GameEditDto;
import com.automappinghomework.domain.models.UserDto;
import com.automappinghomework.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private GameAddDto gameAddDto;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (!this.userService.isLoggedUserAdmin()) {
            System.out.println("Logged user is not ADMIN and can't add games!!!");
        }

        Game game = this.modelMapper.map(gameAddDto, Game.class);
        this.gameRepository.saveAndFlush(game);
    }

    @Override
    public void editGame(long id, GameEditDto gameEditDto) {
        if (!this.userService.isLoggedUserAdmin()) {
            System.out.println("Logged user is not ADMIN and can't edit games!!!");
            return;
        }

        Game game = this.gameRepository.findById(id);
        if (game == null) {
            System.out.println("Game with given id doesn't exist!!!");

        } else {
            game = modelMapper.map(gameEditDto, Game.class);
            game.setId(id);
            this.gameRepository.save(game);
            System.out.println("Edited " + game.getTitle());
        }
    }
}