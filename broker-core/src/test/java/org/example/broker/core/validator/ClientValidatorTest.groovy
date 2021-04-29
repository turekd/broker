package org.example.broker.core.validator

import org.apache.commons.validator.routines.UrlValidator
import org.example.broker.api.domain.Client
import org.example.broker.core.entity.ClientEntity
import org.example.broker.core.repository.ClientRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ClientValidatorTest extends Specification {

    UrlValidator urlValidator = Mock()
    ClientRepository clientRepository = Mock()

    @Subject
    ClientValidator validator

    void setup() {
        validator = new ClientValidator(urlValidator, clientRepository)
    }

    @Unroll
    def 'test validation with topic [#topic] and url [#url] should be #shouldBeValid'() {
        given:
            urlValidator.isValid(null) >> false
            urlValidator.isValid('http://localhost') >> false
            urlValidator.isValid('http://127.0.0.1') >> true
        and:
            clientRepository.findByTopicAndUrl(null, null) >> Optional.empty()
            if ('existing-client' == topic) {
                clientRepository.findByTopicAndUrl('existing-client', url) >> Optional.of(new ClientEntity())
            } else {
                clientRepository.findByTopicAndUrl(topic, url) >> Optional.empty()
            }
        when:
            def result = validator.validate(new Client(
                topic: topic,
                url: url
            ))
        then:
            result.isValid() == shouldBeValid
            result.errors == expectedErrors
        where:
            topic             | url                || shouldBeValid || expectedErrors
            null              | null               || false         || ['Topic can not be empty', 'Provided url is not a valid URL']
            'dummy_'          | null               || false         || ['Topic must match ^[a-z0-9\\-]+$', 'Provided url is not a valid URL']
            'dummy'           | 'http://localhost' || false         || ['Provided url is not a valid URL']
            'existing-client' | 'http://127.0.0.1' || false         || ['Client already exists']
            'dummy'           | 'http://127.0.0.1' || true          || []
    }
}
