package edu.wgu.activitytracker.services

import edu.wgu.activitytracker.datahandlers.DistanceUnitMapper
import edu.wgu.activitytracker.repositories.DistanceUnitRepository
import spock.lang.Specification

class DistanceUnitServiceTest extends Specification {

    private DistanceUnitRepository mockDistanceUnitRepository
    private UserService mockUserService
    private DistanceUnitMapper mockDistanceUnitMapper
    private DistanceUnitService distanceUnitService

    def setup() {
        mockUserService = Mock(UserService.class)
        mockDistanceUnitMapper = Mock(DistanceUnitMapper.class)
        mockDistanceUnitRepository = Mock(DistanceUnitRepository.class)
        distanceUnitService = new DistanceUnitService(mockUserService, mockDistanceUnitMapper, mockDistanceUnitRepository)
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
