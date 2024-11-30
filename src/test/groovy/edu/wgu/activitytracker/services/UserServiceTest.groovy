package edu.wgu.activitytracker.services

import edu.wgu.activitytracker.datahandlers.UserMapper
import edu.wgu.activitytracker.dto.UserDto
import edu.wgu.activitytracker.entities.User
import edu.wgu.activitytracker.repositories.UserRepository
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

@SuppressWarnings("WeakerAccess")
class UserServiceTest extends Specification {

    private UserRepository mockUserRepository
    private UserMapper mockUserMapper
    private PasswordEncoder mockPasswordEncoder
    private AuthenticationService mockAuthenticationService
    private UserService userService

    Integer id
    String username
    String password

    def setup() {
        mockUserRepository = Mock(UserRepository.class)
        mockUserMapper = Mock(UserMapper.class)
        mockPasswordEncoder = Mock(PasswordEncoder.class)
        mockAuthenticationService = Mock(AuthenticationService.class)
        userService = new UserService(mockUserRepository, mockUserMapper, mockPasswordEncoder, mockAuthenticationService)

        id = 1
        username = "user"
        password = "password"
    }

    def "AddUser test"() {
        given:
        var userDto = new UserDto(id, username, password)
        var user = new User(id, username, password)

        when:
        boolean returnedBoolean = userService.addUser(userDto)

        then:
        1 * mockUserMapper.mapDtoToEntity(userDto) >> user
        1 * mockPasswordEncoder.encode(user.getPassword())
        1 * mockUserRepository.save(user) >> user
        1 * mockUserRepository.findById(id) >> Optional.of(user)
        0 * _

        returnedBoolean
    }

    def "GetUserByUsername test"() {
        given:
        var user = new User(id, username, password)

        when:
        var returnedUser = userService.getUserByUsername(username)

        then:
        1 * mockUserRepository. findUserByUsername(username) >> Optional.of(user)
        0 * _

        returnedUser.id == id
        returnedUser.username == username
        returnedUser.password == password
    }

    def "GetCurrentlyLoggedInUser - test"() {
        given:
        var user = new User(id, username, password)

        when:
        User returnedUser = userService.getCurrentlyLoggedInUser()

        then:
        1 * mockAuthenticationService.getAuthentication() >> new TestingAuthenticationToken(username, password)
        1 * mockUserRepository.findUserByUsername(username) >> Optional.of(user)
        0 * _

        returnedUser.username == username
        returnedUser.password == password
    }
}
