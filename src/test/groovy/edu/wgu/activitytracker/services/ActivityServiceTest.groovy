package edu.wgu.activitytracker.services

import edu.wgu.activitytracker.repositories.ActivityRepository
import spock.lang.Specification

class ActivityServiceTest extends Specification {

    private ActivityRepository mockActivityRepository
    private ActivityService activityService

    def setup() {
        mockActivityRepository = Mock(ActivityRepository.class)
        activityService = new ActivityService(mockActivityRepository)
    }

    def "GetAllActivities test"() {
        when:
        activityService.getAllActivities()

        then:
        1 * mockActivityRepository.findAll()
        0 * _
    }

    def "GetActivityByName"() {
        given:
        String name = "name"

        when:
        activityService.getActivityByName(name)

        then:
        1 * mockActivityRepository.findByName(name)
        0 * _
    }
}
