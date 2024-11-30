package edu.wgu.activitytracker.datahandlers

import edu.wgu.activitytracker.dto.UserDto
import edu.wgu.activitytracker.entities.User
import spock.lang.Specification

class UserMapperTest extends Specification {

    private UserMapper userMapper

    Integer userId
    String username = "user"
    String password = "pass"

    def setup() {
        userMapper = new UserMapper()
    }

    def "MapDtoToEntity test"() {
        given:
        var userDto = new UserDto(userId, username, password)

        when:
        var returnedUser = userMapper.mapDtoToEntity(userDto)

        then:
        returnedUser.id == userId
        returnedUser.username == username
        returnedUser.password == password
    }

    def "MapEntityToDto test"() {
        given:
        var user = new User(userId, username, password)

        when:
        var returnedUserDto = userMapper.mapEntityToDto(user)

        then:
        returnedUserDto.id == userId
        returnedUserDto.username == username
        returnedUserDto.password == password
    }
}
