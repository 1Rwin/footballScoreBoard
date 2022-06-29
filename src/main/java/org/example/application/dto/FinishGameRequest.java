package org.example.application.dto;

import java.util.UUID;

public class FinishGameRequest {

    UUID gameId;

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public FinishGameRequest(UUID id){
        this.gameId = id;
    }
}
