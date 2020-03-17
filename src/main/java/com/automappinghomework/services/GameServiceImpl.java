package com.automappinghomework.services;

import com.automappinghomework.domain.entities.Game;
import com.automappinghomework.domain.models.GameAddDto;
import com.automappinghomework.domain.models.GameEditDto;
import com.automappinghomework.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private GameEditDto gameEditDto;

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
    public void editGame(GameEditDto gameEditDto) {
        if (!this.userService.isLoggedUserAdmin()) {
            System.out.println("Logged user is not ADMIN and can't edit games!!!");
            return;
        }

        Game game = this.modelMapper.map(gameEditDto, Game.class);
        this.gameRepository.saveAndFlush(game);
        System.out.println("Edited " + game.getTitle());
    }

    @Override
    public GameEditDto findOneById(long id) {
        Optional<Game> game = this.gameRepository.findById(id);
        return game.map(g -> this.modelMapper.map(g, GameEditDto.class)).orElse(null);
    }

    @Override
    public void deleteGame(long id) {

    }
}