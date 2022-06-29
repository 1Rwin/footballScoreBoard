package org.example.domain.game

import org.example.domain.game.exception.GameInvalidStateException
import org.example.domain.team.Team
import spock.lang.Specification

class GameSpec extends Specification {

    def "should create new game"() {
        given:
        Team homeTeam = Team.newTeam("France")
        Team awayTeam = Team.newTeam("Poland")

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
        ex.message == "Game can't be updated - game is already finished."
    }

    def "should not finish game - game is already finished"() {
        given:
        Game game = createFinishedGame()

        when:
        game.finish()

        then:
        GameInvalidStateException ex = thrown(GameInvalidStateException)
        ex.message == "Game can't be updated - game is already finished."
    }

    def "should finish game"() {
        given:
        Game game = createNewGame()

        when:
        game.finish()

        then:
        noExceptionThrown()
        game.isFinished
    }

    def "should create result for specific game"() {
        given:
        Game game = createFinishedGame()

        when:
        game.createGameResult()

        then:
        game.gameResult == "France 0 - Poland 0"

    }

    private Game createNewGame() {
        Team homeTeam = Team.newTeam("France")
        Team awayTeam = Team.newTeam("Poland")
        Game.startNew(homeTeam, awayTeam)
    }

    private Game createFinishedGame() {
        Team homeTeam = Team.newTeam("France")
        Team awayTeam = Team.newTeam("Poland")
        Game newGame = Game.startNew(homeTeam, awayTeam)
        newGame.update(0, 0, true)
        newGame
    }
}
