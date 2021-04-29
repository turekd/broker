package org.example.broker.core.service.broker.client

import org.example.broker.api.domain.Client
import org.example.broker.core.converter.ClientConverter
import org.example.broker.core.entity.ClientEntity
import org.example.broker.core.repository.ClientRepository
import org.example.broker.core.repository.MessageRepository
import org.example.broker.core.service.broker.publisher.MessagePublisher
import org.example.broker.core.validator.ClientValidator
import org.example.broker.core.validator.ValidationException
import org.example.broker.core.validator.ValidationResult
import spock.lang.Specification
import spock.lang.Subject

class BrokerClientServiceImplTest extends Specification {

    ClientValidator clientValidator = Mock()
    ClientRepository clientRepository = Mock()
    MessageRepository messageRepository = Mock()
    MessagePublisher messagePublisher = Mock()
    ClientConverter clientConverter = Mock()

    @Subject
    BrokerClientServiceImpl service

    void setup() {
        service = new BrokerClientServiceImpl(clientValidator, clientRepository, messageRepository, clientConverter)
    }

    def "should throw exception when client is invalid"() {
        given:
            def client = new Client()
            1 * clientValidator.validate(client) >> new ValidationResult(isValid: false)
        when:
            service.register(messagePublisher, client)
        then:
            thrown(ValidationException.class)
    }

    def "should add client"() {
        given:
            def client = new Client(topic: 'topic', url: 'url')
            def entity = new ClientEntity(topic: 'topic', url: 'url')
            1 * clientValidator.validate(client) >> new ValidationResult(isValid: true)
            1 * clientConverter.toEntity(client) >> entity
        when:
            service.register(messagePublisher, client)
        then:
            1 * clientRepository.save(entity)
            noExceptionThrown()
    }
}
