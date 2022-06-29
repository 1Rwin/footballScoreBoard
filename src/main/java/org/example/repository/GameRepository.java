package org.example.repository;

import org.example.domain.game.Game;

import java.util.List;
import java.util.UUID;

public interface GameRepository {

    List<Game> findByGameId(UUID gameId);

    Game getById(UUID gameId);

    Game save(Game game);

    void removeAll();
}
