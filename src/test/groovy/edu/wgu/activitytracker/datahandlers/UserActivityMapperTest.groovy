package edu.wgu.activitytracker.datahandlers

import edu.wgu.activitytracker.dto.UserActivityDto
import edu.wgu.activitytracker.entities.Activity
import edu.wgu.activitytracker.entities.DistanceUnit
import edu.wgu.activitytracker.entities.User
import edu.wgu.activitytracker.entities.UserActivity
import edu.wgu.activitytracker.services.ActivityService
import edu.wgu.activitytracker.services.DistanceUnitService
import edu.wgu.activitytracker.services.UserService
import spock.lang.Specification

import java.time.Duration
import java.time.LocalDateTime

class UserActivityMapperTest extends Specification {

    private UserService mockUserService
    private ActivityService mockActivityService
    private DistanceUnitService mockDistanceUnitService
    private UserActivityMapper userActivityMapper

    Integer id
    LocalDateTime startDateTime
    LocalDateTime endDateTime
    Duration duration
    Double distance
    String note
    Integer userId
    User user
    Integer activityId
    Activity activity
    String activityName
    Integer distanceUnitId
    DistanceUnit distanceUnit
    String distanceUnitName

    def setup() {
        mockUserService = Mock(UserService.class)
        mockActivityService = Mock(ActivityService.class)
        mockDistanceUnitService = Mock(DistanceUnitService.class)
        userActivityMapper = new UserActivityMapper(mockUserService, mockActivityService, mockDistanceUnitService)

        id = 1
        startDateTime = LocalDateTime.now()
        endDateTime = LocalDateTime.now()
        duration = Duration.between(startDateTime, endDateTime)
        distance = 2.0
        note = "new note"

        userId = 2
        user = new User()
        user.setId(userId)

        activityId = 3
        activityName = "activity"
        activity = new Activity(activityId, activityName)

        distanceUnitId = 4
        distanceUnitName = "Yard"
        distanceUnit = new DistanceUnit(distanceUnitId, distanceUnitName)
    }


    def "MapDtoToEntity test"() {
        given:
        var userActivityDto = UserActivityDto.builder()
            .id(id)
            .userId(userId)
            .activityName(activity.getName())
            .startDateTime(startDateTime)
            .endDateTime(endDateTime)
            .duration(duration)
            .distance(distance)
            .distanceUnitName(distanceUnit.getName())
            .note(note)
            .build()

        when:
        var returnedEntity = userActivityMapper.mapDtoToEntity(userActivityDto)

        then:
        1 * mockUserService.getCurrentlyLoggedInUser() >> user
        1 * mockActivityService.getActivityByName(activity.getName()) >> activity
        1 * mockDistanceUnitService.getDistanceUnitByName(distanceUnit.getName()) >> distanceUnit
        0 * _

        returnedEntity.id == id
        returnedEntity.user == user
        returnedEntity.activity == activity
        returnedEntity.startDateTime == startDateTime
        returnedEntity.endDateTime == endDateTime
        returnedEntity.distance == distance
        returnedEntity.distanceUnit == distanceUnit
        returnedEntity.note == note
    }

    def "MapEntityToDto test"() {
        given:
        var userActivity = UserActivity.builder()
        .id(id)
        .user(user)
        .activity(activity)
        .startDateTime(startDateTime)
        .endDateTime(endDateTime)
        .duration(duration)
        .distance(distance)
        .distanceUnit(distanceUnit)
        .note(note)
        .build()

        when:
        var returnedDto = userActivityMapper.mapEntityToDto(userActivity)

        then:
        returnedDto.id == id
        returnedDto.userId == user.id
        returnedDto.activityName == activity.name
        returnedDto.startDateTime == startDateTime
        returnedDto.endDateTime == endDateTime
        returnedDto.duration == duration
        returnedDto.distance == distance
        returnedDto.distanceUnitName == distanceUnit.name
        returnedDto.note == note
    }
}
