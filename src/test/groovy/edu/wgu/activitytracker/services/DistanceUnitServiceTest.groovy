package edu.wgu.activitytracker.services

import edu.wgu.activitytracker.repositories.DistanceUnitRepository
import spock.lang.Specification

class DistanceUnitServiceTest extends Specification {

    private DistanceUnitRepository mockDistanceUnitRepository
    private DistanceUnitService distanceUnitService

    def setup() {
        mockDistanceUnitRepository = Mock(DistanceUnitRepository.class)
        distanceUnitService = new DistanceUnitService(mockDistanceUnitRepository)
    }

    def "GetAllDistanceUnit test"() {
        when:
        distanceUnitService.getAllDistanceUnits()

        then:
        1 * mockDistanceUnitRepository.findAll()
        0 * _
    }

    def "GetDistanceUnitByName test"() {
        given:
        String unitName = "name"

        when:
        distanceUnitService.getDistanceUnitByName(unitName)

        then:
        1 * mockDistanceUnitRepository.findByName(unitName)
        0 * _
    }
}
