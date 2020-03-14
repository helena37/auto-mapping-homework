package com.automappinghomework.services;

import com.automappinghomework.domain.entities.Game;
import com.automappinghomework.domain.models.GameAddDto;
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
            System.out.println("Logged user is not ADMIN and can't add / edit or remove games!!!");
        }

        Game game = this.modelMapper.map(gameAddDto, Game.class);
        this.gameRepository.saveAndFlush(game);
    }
}
