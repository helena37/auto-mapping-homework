package com.automappinghomework.services;

import com.automappinghomework.domain.entities.Game;
import com.automappinghomework.domain.models.dtos.GameAddDto;
import com.automappinghomework.domain.models.dtos.GameDeleteDto;
import com.automappinghomework.domain.models.dtos.GameEditDto;
import com.automappinghomework.domain.models.views.GameSingleTitleDetailsViewDto;
import com.automappinghomework.domain.models.views.GameTitleAndPriceViewDto;
import com.automappinghomework.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private GameDeleteDto gameDeleteDto;

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
    public GameDeleteDto findById(long id) {
        Optional<Game> game = this.gameRepository.findById(id);
        return game.map(g -> this.modelMapper.map(g, GameDeleteDto.class)).orElse(null);
    }

    @Override
    public void deleteGame(GameDeleteDto gameDeleteDto) {
        Game game = this.modelMapper.map(gameDeleteDto, Game.class);
        this.gameRepository.deleteById(gameDeleteDto.getId());
    }

    @Override
    public Set<GameTitleAndPriceViewDto> getAllGameTitlesAndPrices() {
        return this.gameRepository
                .findAll()
                .stream()
                .map(game -> this.modelMapper.map(game, GameTitleAndPriceViewDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public GameSingleTitleDetailsViewDto getSingleTitleDetails(String title) {
        if (!this.gameRepository.existsByTitleEquals(title)) {
           System.out.println("Game with the given title doesn't exist in database!!!");
        }

        return this.modelMapper
                .map(this.gameRepository.getGameByTitleEquals(title),
                        GameSingleTitleDetailsViewDto.class);
    }


}