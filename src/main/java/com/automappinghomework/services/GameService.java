package com.automappinghomework.services;

import com.automappinghomework.domain.entities.Game;
import com.automappinghomework.domain.models.dtos.GameAddDto;
import com.automappinghomework.domain.models.dtos.GameDeleteDto;
import com.automappinghomework.domain.models.dtos.GameEditDto;
import com.automappinghomework.domain.models.views.GameSingleTitleDetailsViewDto;
import com.automappinghomework.domain.models.views.GameTitleAndPriceViewDto;

import java.util.Set;

public interface GameService {
    void addGame(GameAddDto gameAddDto);
    void editGame(GameEditDto gameEditDto);
    GameEditDto findOneById(long id);
    GameDeleteDto findById(long id);
    void deleteGame(GameDeleteDto gameDeleteDto);
    Set<GameTitleAndPriceViewDto> getAllGameTitlesAndPrices();
    GameSingleTitleDetailsViewDto getSingleTitleDetails(String title);
}
