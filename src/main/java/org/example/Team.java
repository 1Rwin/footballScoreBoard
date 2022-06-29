package org.example;

import java.util.UUID;

public class Team {

    UUID id;
    String name;

    public static Team of(String name) {
        return new Team(name);
    }

    private Team(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
