package org.example.broker.core.repository;

import org.example.broker.core.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByTopicAndUrl(String topic, String url);

    Set<ClientEntity> findAllByTopic(String topic);

}
