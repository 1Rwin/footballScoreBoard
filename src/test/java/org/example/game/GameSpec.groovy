package org.example.game

import org.example.game.Game
import org.example.team.Team
import spock.lang.Specification

class GameSpec extends Specification {

    def"should create new game"(){
        given:
        Team homeTeam = Team.of("France")
        Team awayTeam = Team.of("Poland")

        when:
        Game newGame = Game.startNew(homeTeam,awayTeam)

        then:
        newGame.awayTeam.name == "Poland"
        newGame.homeTeam.name == "France"
        newGame.awayTeamScore == 0
        newGame.homeTeamScore == 0
        !newGame.isFinished
    }
}
