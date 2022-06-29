package org.example.domain.team

import spock.lang.Specification

class TeamSpec extends Specification {

    def "should create new team"() {
        when:
        Team newTeam = Team.newTeam("France")

        then:
        newTeam.name == "France"
    }
}
