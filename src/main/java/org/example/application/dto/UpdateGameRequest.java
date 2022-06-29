package org.example.application.dto;

import java.util.UUID;

public class UpdateGameRequest {
    UUID gameId;
    int homeTeamScore;
    int awayTeamScore;
    boolean isFinished;

    public UpdateGameRequest(UUID gameId, int homeTeamScore, int awayTeamScore, boolean isFinished) {
        this.gameId = gameId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.isFinished = isFinished;
    }

    public UUID getGameId() {
        return gameId;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public boolean isFinished() {
        return isFinished;
    }

}
