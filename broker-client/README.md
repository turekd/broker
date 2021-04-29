## Broker Client

It is an interface helping to integrate with the Broker. To use it, all you need to do is creating an instance of
BrokerClient, e.g.

```
@Bean
BrokerClient brokerClient(RestTemplateBuilder restTemplateBuilder, @Value("${broker.server.url}") String url){
    return new BrokerRestClient(url, restTemplateBuilder.build());
}
```
