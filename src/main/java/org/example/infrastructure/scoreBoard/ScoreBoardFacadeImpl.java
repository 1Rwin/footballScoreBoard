package org.example.infrastructure.scoreBoard;

import org.example.application.ScoreBoardFacade;
import org.example.application.dto.CreateGameRequest;
import org.example.application.dto.FinishGameRequest;
import org.example.application.dto.UpdateGameRequest;
import org.example.domain.game.Game;
import org.example.domain.team.Team;
import org.example.repository.GameRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardFacadeImpl implements ScoreBoardFacade {

    private final GameRepository gameRepository;

    public ScoreBoardFacadeImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void startGame(CreateGameRequest createGameRequest) {
        Game newGame = Game.startNew(Team.newTeam(createGameRequest.getHomeTeamName()),
                Team.newTeam(createGameRequest.getAwayTeamName()));
        gameRepository.save(newGame);
    }

    @Override
    public void updateGame(UpdateGameRequest updateGameRequest) {
        Game gameToUpdate = gameRepository.getById(updateGameRequest.getGameId());
        gameToUpdate.update(updateGameRequest.getHomeTeamScore(), updateGameRequest.getAwayTeamScore(), updateGameRequest.isFinished());
        gameRepository.save(gameToUpdate);
    }

    @Override
    public void finishGame(FinishGameRequest finishGameRequest) {
        Game gameToFinish = gameRepository.getById(finishGameRequest.getGameId());
        gameToFinish.finish();
        gameRepository.save(gameToFinish);
    }

    @Override
    public List<String> getSummary() {
        return gameRepository.findAll().stream()
                .map(Game::getGameResult)
                .collect(Collectors.toList());
    }
}
