package edu.wgu.activitytracker.services

import edu.wgu.activitytracker.datahandlers.UserActivityMapper
import edu.wgu.activitytracker.dto.UserActivityDto
import edu.wgu.activitytracker.entities.User
import edu.wgu.activitytracker.entities.UserActivity
import edu.wgu.activitytracker.repositories.UserActivityRepository
import spock.lang.Specification

import java.time.LocalDateTime

class UserActivityServiceTest extends Specification {

    private UserActivityRepository mockUserActivityRepository
    private UserActivityMapper mockUserActivityMapper
    private UserService mockUserService
    private UserActivityService userActivityService

    def setup() {
        mockUserActivityRepository = Mock(UserActivityRepository.class)
        mockUserActivityMapper = Mock(UserActivityMapper.class)
        mockUserService = Mock(UserService.class)
        userActivityService = new UserActivityService(mockUserActivityRepository, mockUserActivityMapper, mockUserService)
    }

    def "GetLoggedInUsersUserActivities - test"() {
        given:
        var user = new User(1, "username", "password")

        var userActivityId = 2
        var note = "note"
        var startDateTime = LocalDateTime.now()

        var userActivity = new UserActivity()
        userActivity.id = userActivityId
        userActivity.note = note
        userActivity.startDateTime = startDateTime

        var userActivityDto = new UserActivityDto()
        userActivityDto.id = userActivityId
        userActivityDto.note = note
        userActivityDto.startDateTime = startDateTime

        when:
        var returnedActivities = userActivityService.getLoggedInUsersUserActivities()

        then:
        1 * mockUserService.getCurrentlyLoggedInUser() >> user
        1 * mockUserActivityRepository.getUserActivitiesByUserId(user.id) >> Optional.of(List.of(userActivity))
        1 * mockUserActivityMapper.mapEntityToDto(userActivity) >> userActivityDto
        0 * _

        returnedActivities.size() == 1
        returnedActivities[0].id == userActivityId
        returnedActivities[0].note == note
        returnedActivities[0].startDateTime == startDateTime

    }

    def "AddUserActivity test"() {
        given:
        var userActivityDto = new UserActivityDto()
        var userActivity = new UserActivity()

        when:
        userActivityService.saveUserActivity(userActivityDto)

        then:
        1 * mockUserActivityMapper.mapDtoToEntity(userActivityDto) >> userActivity
        1 * mockUserActivityRepository.save(userActivity)
        0 * _
    }
}
