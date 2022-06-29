package org.example.repository;

import org.example.domain.game.Game;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface GameRepository {

    List<Game> findByGameId(UUID gameId);

    Game getById(UUID gameId);

    void save(Game game);

    void removeAll();

    void saveAll(Collection<Game> games);

    List<Game> findAll();
}
