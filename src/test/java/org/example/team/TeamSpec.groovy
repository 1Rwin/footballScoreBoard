package org.example.team

import org.example.team.Team
import spock.lang.Specification

class TeamSpec extends Specification {

    def "should create new team"() {
        when:
        Team newTeam = Team.of("France")

        then:
        newTeam.name == "France"
    }
}
