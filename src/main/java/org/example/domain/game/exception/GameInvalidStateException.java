package org.example.domain.game.exception;

public class GameInvalidStateException extends RuntimeException {

    public GameInvalidStateException(String message) {
        super(message);
    }
}
