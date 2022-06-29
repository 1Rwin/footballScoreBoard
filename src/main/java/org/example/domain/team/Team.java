package org.example.domain.team;

import java.util.Objects;
import java.util.UUID;

public class Team {
    UUID id;
    public String name;

    public static Team newTeam(String name) {
        return new Team(name);
    }

    private Team(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
