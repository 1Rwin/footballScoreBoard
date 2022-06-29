package org.example.domain.game;

import org.example.domain.game.exception.GameInvalidStateException;
import org.example.domain.team.Team;

import java.util.Objects;
import java.util.UUID;

public class Game {
    public final UUID id;
    Team homeTeam;
    Team awayTeam;
    Integer homeTeamScore;
    Integer awayTeamScore;
    Boolean isFinished;
    String gameResult;

    public static Game startNew(Team homeTeam, Team awayTeam) {
        return new Game(homeTeam, awayTeam);
    }

    public void update(int newHomeTeamScore, int newAwayTeamScore, boolean newIsFinished) {
        validationIsInProgress();
        this.awayTeamScore = newAwayTeamScore;
        this.homeTeamScore = newHomeTeamScore;
        this.isFinished = newIsFinished;
    }

    public void createGameResult() {
        validationGameResult();
        this.gameResult =
                homeTeam.name + " " + homeTeamScore +
                        " - " +
                        awayTeam.name + " " + awayTeamScore;
    }

    private void validationGameResult() {
        if (!isFinished) {
            throw new GameInvalidStateException("Game Result can't be created -  game is not finished.");
        }
    }

    public void finish() {
        validationIsInProgress();
        this.isFinished = true;
    }

    private void validationIsInProgress() {
        if (isFinished) {
            throw new GameInvalidStateException("Game can't be updated - game is already finished.");
        }
    }

    private Game(Team homeTeam, Team awayTeam) {
        this.id = UUID.randomUUID();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.isFinished = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(homeTeam, game.homeTeam) && Objects.equals(awayTeam, game.awayTeam) && Objects.equals(homeTeamScore, game.homeTeamScore) && Objects.equals(awayTeamScore, game.awayTeamScore) && Objects.equals(isFinished, game.isFinished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, homeTeam, awayTeam, homeTeamScore, awayTeamScore, isFinished);
    }
}
