package org.example.domain.game

import org.example.domain.game.exception.GameInvalidStateException
import org.example.domain.team.Team
import spock.lang.Specification

class GameSpec extends Specification {

    def "should create new game"() {
        given:
        Team homeTeam = Team.of("France")
        Team awayTeam = Team.of("Poland")

        when:
        Game newGame = Game.startNew(homeTeam, awayTeam)

        then:
        newGame.awayTeam.name == "Poland"
        newGame.homeTeam.name == "France"
        newGame.awayTeamScore == 0
        newGame.homeTeamScore == 0
        !newGame.isFinished
    }

    def "should update game"() {
        given:
        Game newGame = createNewGame()
        int newHomeScore = 1
        int newAwayScore = 0
        boolean newIsFinished = false

        when:
        newGame.update(newHomeScore, newAwayScore, newIsFinished)

        then:
        newGame.awayTeam.name == "Poland"
        newGame.homeTeam.name == "France"
        newGame.awayTeamScore == 0
        newGame.homeTeamScore == 1
        !newGame.isFinished
    }

    def "should not update game - game is already finished"() {
        given:
        Game game = createFinishedGame()
        int newHomeScore = 1
        int newAwayScore = 0
        boolean newIsFinished = false

        when:
        game.update(newHomeScore, newAwayScore, newIsFinished)

        then:
        GameInvalidStateException ex = thrown(GameInvalidStateException)
        ex.message == "Game can't be updated because it is already finished."
    }

    private Game createNewGame() {
        Team homeTeam = Team.of("France")
        Team awayTeam = Team.of("Poland")
        Game.startNew(homeTeam, awayTeam)
    }

    private Game createFinishedGame() {
        Team homeTeam = Team.of("France")
        Team awayTeam = Team.of("Poland")
        Game newGame = Game.startNew(homeTeam, awayTeam)
        newGame.update(0, 0, true)
        newGame
    }
}
