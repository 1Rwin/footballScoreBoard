package org.example.infrastructure.scoreBoard

import org.example.application.dto.CreateGameRequest
import org.example.application.dto.FinishGameRequest
import org.example.application.dto.UpdateGameRequest
import org.example.domain.game.Game
import org.example.domain.team.Team
import org.example.infrastructure.repository.InMemoryGameRepository
import spock.lang.Specification

class ScoreBoardFacadeImplSpec extends Specification {

    def "should create and save new game"() {
        given:
        InMemoryGameRepository gameRepository = new InMemoryGameRepository()
        ScoreBoardFacadeImpl scoreBoardFacade = new ScoreBoardFacadeImpl(gameRepository)
        CreateGameRequest createGameRequest = createGameRequest()


        when:
        scoreBoardFacade.startGame(createGameRequest)

        then:
        gameRepository.findAll().size() == 1
        gameRepository.findAll().get(0).getAwayTeamName() == "Poland"
        gameRepository.findAll().get(0).getHomeTeamName() == "Spain"

        gameRepository.removeAll()
    }

    def "should update existing game"() {
        given:
        InMemoryGameRepository gameRepository = new InMemoryGameRepository()
        ScoreBoardFacadeImpl scoreBoardFacade = new ScoreBoardFacadeImpl(gameRepository)
        CreateGameRequest createGameRequest = createGameRequest()

        when:
        scoreBoardFacade.startGame(createGameRequest)
        scoreBoardFacade.updateGame(updateGameRequest(gameRepository.findAll().get(0).id))

        then:
        gameRepository.findAll().size() == 1
        gameRepository.findAll().get(0).getAwayTeamName() == "Poland"
        gameRepository.findAll().get(0).getHomeTeamName() == "Spain"
        gameRepository.findAll().get(0).getGameResult() == "Spain 1 - Poland 0"

        gameRepository.removeAll()
    }

    def "should finish existing game"() {
        given:
        InMemoryGameRepository gameRepository = new InMemoryGameRepository()
        ScoreBoardFacadeImpl scoreBoardFacade = new ScoreBoardFacadeImpl(gameRepository)
        CreateGameRequest createGameRequest = createGameRequest()

        when:
        scoreBoardFacade.startGame(createGameRequest)
        scoreBoardFacade.finishGame(finishGameRequest(gameRepository.findAll().get(0).id))

        then:
        gameRepository.findAll().size() == 1
        gameRepository.findAll().get(0).getAwayTeamName() == "Poland"
        gameRepository.findAll().get(0).getHomeTeamName() == "Spain"
        gameRepository.findAll().get(0).getGameResult() == "Spain 0 - Poland 0"
        gameRepository.findAll().get(0).getIsGameFinished()

        gameRepository.removeAll()
    }

    def "should return summary"() {
        given:
        InMemoryGameRepository gameRepository = new InMemoryGameRepository()
        ScoreBoardFacadeImpl scoreBoardFacade = new ScoreBoardFacadeImpl(gameRepository)

        when:
        gameRepository.saveAll(gamesToSave())
        List<String> summaryGamesList = scoreBoardFacade.getSummary()

        then:
        summaryGamesList.get(0) == "Uruguay 6 - Italy 6"
        summaryGamesList.get(1) == "Spain 10 - Brazil 2"
        summaryGamesList.get(2) == "Mexico 0 - Canada 5"
        summaryGamesList.get(3) == "Argentina 3 - Australia 1"
        summaryGamesList.get(4) == "Germany 2 - France 2"

        gameRepository.removeAll()
    }


    private CreateGameRequest createGameRequest() {
        String homeTeamName = "Spain"
        String awayTeamName = "Poland"
        new CreateGameRequest(homeTeamName, awayTeamName)
    }

    private UpdateGameRequest updateGameRequest(UUID gameId) {
        int newHomeScore = 1
        int newAwayScore = 0
        boolean newIsFinished = true
        new UpdateGameRequest(gameId, newHomeScore, newAwayScore, newIsFinished)
    }

    private FinishGameRequest finishGameRequest(UUID gameId) {
        new FinishGameRequest(gameId)
    }

    private List<Game> gamesToSave() {
        List<Game> newGamesList = new LinkedList<>()
        newGamesList.add(createFinishedGame("Uruguay", "Italy", 6, 6))
        newGamesList.add(createFinishedGame("Spain", "Brazil", 10, 2))
        newGamesList.add(createFinishedGame("Mexico", "Canada", 0, 5))
        newGamesList.add(createFinishedGame("Argentina", "Australia", 3, 1))
        newGamesList.add(createFinishedGame("Germany", "France", 2, 2))
        newGamesList
    }

    private Game createFinishedGame(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore) {
        Game newGame = Game.startNew(Team.newTeam(homeTeamName), Team.newTeam(awayTeamName))
        newGame.update(homeTeamScore, awayTeamScore, true)
        newGame
    }

}
