package org.example.domain.game;

import org.example.domain.team.Team;

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
