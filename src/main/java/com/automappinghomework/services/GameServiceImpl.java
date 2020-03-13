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
    private final ModelMapper modelMapper;
    private GameAddDto gameAddDto;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        Game game = this.modelMapper.map(gameAddDto, Game.class);

    }
}
