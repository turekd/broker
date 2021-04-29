package org.example.broker.core.controller;

import lombok.RequiredArgsConstructor;
import org.example.broker.api.domain.Message;
import org.example.broker.core.service.topic.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topic")
@RequiredArgsConstructor
public class TopicRestController {

    private final TopicService restTopicService;

    @PutMapping("/{topic}/message")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void publishMessageOnTopic(
        @PathVariable("topic") String topic,
        @RequestBody Message message
    ) {
        restTopicService.publishMessageOnTopic(topic, message);
    }

}
