package edu.wgu.activitytracker.services

import edu.wgu.activitytracker.entities.User
import edu.wgu.activitytracker.repositories.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification

class JpaUserDetailsServiceTest extends Specification {

    private UserRepository mockUserRepository
    private JpaUserDetailsService jpaUserDetailsService

    def setup() {
        mockUserRepository = Mock(UserRepository.class)
        jpaUserDetailsService = new JpaUserDetailsService(mockUserRepository)
    }

    def "LoadUserByUsername test - happy path"() {
        given:
        Integer id = 1
        String username = "user"
        String password = "pass"

        when:
        var returnedUserDetails = jpaUserDetailsService.loadUserByUsername(username)

        then:
        1 * mockUserRepository.findUserByUsername(username) >> Optional.of(new User(id, username, password))
        0 * _

        returnedUserDetails.username == username
        returnedUserDetails.password == password
    }

    def "LoadUserByUsername test - exception"() {
        given:
        String username = "user"

        when:
        jpaUserDetailsService.loadUserByUsername(username)

        then:
        1 * mockUserRepository.findUserByUsername(username) >> Optional.empty()
        0 * _

        thrown UsernameNotFoundException
    }
}
