package org.example.broker.core.validator

import org.example.broker.api.domain.Message
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDateTime

class MessageValidatorTest extends Specification {

    @Subject
    MessageValidator validator = new MessageValidator()

    @Unroll
    def 'test validation should return #shouldBeValid'() {
        when:
            def result = validator.validate(new Message(
                expiresAt: expiresAt,
                content: content
            ))
        then:
            result.isValid() == shouldBeValid
            result.errors == expectedErrors
        where:
            content | expiresAt                           || shouldBeValid || expectedErrors
            null    | null                                || false         || ['Content can not be empty', 'Expiration datetime can not be empty']
            ''      | null                                || false         || ['Content can not be empty', 'Expiration datetime can not be empty']
            '{}'    | LocalDateTime.now().minusMinutes(1) || false         || ['Expiration datetime can not be a past date']
            '{}'    | LocalDateTime.now().plusMinutes(1)  || true          || []
    }

}
