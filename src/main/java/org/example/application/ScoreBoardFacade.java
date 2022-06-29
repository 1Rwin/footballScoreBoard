package org.example.application;

import org.example.application.dto.CreateGameRequest;
import org.example.application.dto.FinishGameRequest;
import org.example.application.dto.UpdateGameRequest;

import java.util.List;

public interface ScoreBoardFacade {

    void startGame(CreateGameRequest createGameRequest);

    void updateGame(UpdateGameRequest updateGameRequest);

    void finishGame(FinishGameRequest FinishGameRequest);

    List<String> getSummary();
}
