package com.automappinghomework.services;

import com.automappinghomework.domain.entities.Game;
import com.automappinghomework.domain.models.GameAddDto;
import com.automappinghomework.domain.models.GameEditDto;

public interface GameService {
    void addGame(GameAddDto gameAddDto);
    void editGame(GameEditDto gameEditDto);
    GameEditDto findOneById(long id);
    void deleteGame(long id);
}
