package edu.wgu.activitytracker.validation

import edu.wgu.activitytracker.entities.User
import edu.wgu.activitytracker.services.UserService
import jakarta.validation.ConstraintValidatorContext
import spock.lang.Specification

class UniqueUserValidatorTest extends Specification {

    private UserService mockUserService
    private UniqueUserValidator uniqueUserValidator

    def setup() {
        mockUserService = Mock(UserService.class)
        uniqueUserValidator = new UniqueUserValidator(mockUserService)
    }

    def "test IsValid"() {
        given:
        ConstraintValidatorContext constraintValidatorContext = Mock(ConstraintValidatorContext.class)

        when:
        boolean isValid = uniqueUserValidator.isValid(username, constraintValidatorContext)

        then:
        if (username != null && !username.isEmpty()) {
            1 * mockUserService.getUserByUsername(username) >> user
        }
        0 * _

        isValid == expectedValidity

        where:
        username | user | expectedValidity
        null     | null | true
        ""       | null | true
        "John"   | null | true
        "John"   | new User() | false
    }
}
