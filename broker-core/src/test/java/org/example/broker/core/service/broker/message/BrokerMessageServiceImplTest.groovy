package org.example.broker.core.service.broker.message

import org.example.broker.api.domain.Client
import org.example.broker.api.domain.Message
import org.example.broker.core.converter.ClientConverter
import org.example.broker.core.converter.MessageConverter
import org.example.broker.core.entity.ClientEntity
import org.example.broker.core.entity.MessageEntity
import org.example.broker.core.repository.ClientRepository
import org.example.broker.core.repository.MessageRepository
import org.example.broker.core.service.broker.publisher.MessagePublisher
import org.example.broker.core.validator.MessageValidator
import org.example.broker.core.validator.ValidationException
import org.example.broker.core.validator.ValidationResult
import spock.lang.Specification
import spock.lang.Subject

class BrokerMessageServiceImplTest extends Specification {

    ClientRepository clientRepository = Mock()
    MessageRepository messageRepository = Mock()
    MessageValidator messageValidator = Mock()
    MessagePublisher messagePublisher = Mock()
    ClientConverter clientConverter = Mock()
    MessageConverter messageConverter = Mock()

    @Subject
    BrokerMessageServiceImpl service

    void setup() {
        service = new BrokerMessageServiceImpl(clientRepository, messageRepository, messageValidator, clientConverter, messageConverter)
    }

    def "should throw exception when message is invalid"() {
        given:
            def message = new Message()
            1 * messageValidator.validate(message) >> new ValidationResult(isValid: false)
        when:
            service.publish(messagePublisher, 'dummy', message)
        then:
            thrown(ValidationException.class)
    }

    def "should add message and propagate it throughout clients"() {
        given:
            def topic = 'dummy'
            def content = '{}'
            def message = new Message(content: content)
            def entity = new MessageEntity(content: content)
            def clients = [new Client(topic: topic, url: 'http://127.0.0.1')]
            1 * messageValidator.validate(message) >> new ValidationResult(isValid: true)
            1 * messageConverter.toEntity(message, topic) >> entity
        and:
            1 * clientRepository.findAllByTopic(topic) >> [new ClientEntity(topic: topic, url: 'url')]
            1 * clientConverter.fromEntity(new ClientEntity(topic: topic, url: 'url')) >> new Client(topic: topic, url: 'url')

        when:
            service.publish(messagePublisher, topic, message)
        then:
            1 * messageRepository.save(entity)
            1 * messagePublisher.publishMessage([new Client(topic: topic, url: 'url')].toSet(), content)
    }
}
