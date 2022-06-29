package org.example.infrastructure;

import org.example.domain.game.Game;
import org.example.repository.GameRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryGameRepository implements GameRepository {

    private static final Map<UUID, Game> GAMES = new ConcurrentHashMap<>();

    @Override
    public List<Game> findByGameId(UUID gameId) {
        return GAMES.values().stream()
                .filter(game -> game.id.equals(gameId))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Game getById(UUID gameId) {
        return GAMES.get(gameId);
    }

    @Override
    public Game save(Game game) {
        Objects.requireNonNull(game, "Saved game cannot be null");
        GAMES.put(game.id, game);
        return game;
    }

    @Override
    public void removeAll() {
        GAMES.clear();
    }
}
