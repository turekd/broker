package org.example.broker.core.repository;

import org.example.broker.core.entity.MessageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {


    @Query(value = "select * from messages where expires_at < now()", nativeQuery = true)
    List<MessageEntity> findAllByTopic(String topic);

    @Modifying
    @Query(value = "DELETE FROM messages WHERE expires_at < now()", nativeQuery = true)
    void deleteObsolete();

}
