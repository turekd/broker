# Broker Core

It is an application allowing sharing messages from publishers to subscribers.

## Broker REST API

Uses HTTP as a transportation method to publish messages and register clients.

### Starting the application

#### Prerequisites

You need to have installed:
* Java 11
* Maven min. 3.6
* PostgreSQL

#### Instruction

1. Build `broker-api`.
1. Build the application `mvn clean install`.
1. Set environment properties to setup JDBC:
    * `infra.db.url` - containing JDBC url,
    * `infra.db.username` - DB username,
    * `infra.db.password` - DB password
1. Start the app `mvn spring-boot:run`. 
