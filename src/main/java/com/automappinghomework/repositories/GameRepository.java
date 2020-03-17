package com.automappinghomework.repositories;

import com.automappinghomework.domain.entities.Game;
import com.automappinghomework.domain.models.GameEditDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    GameEditDto findById(long id);
}
