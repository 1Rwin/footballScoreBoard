package org.example;

import java.util.Objects;
import java.util.UUID;

public class Game {
    UUID id;
    Team homeTeam;
    Team awayTeam;
    Integer homeTeamScore;
    Integer awayTeamScore;
    Boolean isFinished;

    public static Game startNew(Team homeTeam, Team awayTeam) {
        return new Game(homeTeam, awayTeam);
    }

    private Game(Team homeTeam, Team awayTeam) {
        this.id = UUID.randomUUID();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.isFinished = false;
    }
}
