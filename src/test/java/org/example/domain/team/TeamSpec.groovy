package org.example.domain.team

import org.example.domain.team.Team
import spock.lang.Specification

class TeamSpec extends Specification {

    def "should create new team"() {
        when:
        Team newTeam = Team.of("France")

        then:
        newTeam.name == "France"
    }
}
