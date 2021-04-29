It is a test application of broker-core module. Uses Broker Client.

### Run instruction
To run it, firstly set up the broker-core.

#### Testing already published messages
It publishes some messages and then registers clients. The goal is to receive already published messages.

1. Start PublisherApplication. It publishes a few messages on different topics. 
2. Wait at least 5 seconds. This way you test if the broker does not sent expired messages.
3. Start SubscriberApplication. It registers clients and subscribes to the topic.

As a result, in the console you should see incoming messages. All of them have the same content 'should be delivered'.
None of the message has 'NOT' in the content since these messages expires 5 seconds after they have been published.

#### Testing 
It registers clients and then publishes messages.

1. Start SubscriberApplication. It registers clients and subscribes to the topic.
2. Start PublisherApplication. It publishes a few messages on different topics. 

Now messages containing 'should be delivered' and 'should NOT be delivered' should be logged.
