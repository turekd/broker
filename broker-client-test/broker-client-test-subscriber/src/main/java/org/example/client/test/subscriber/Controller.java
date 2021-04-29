package org.example.client.test.subscriber;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("subscriber/{topic}")
    public void receiveBrokerMessage(@PathVariable("topic") String topic, @RequestBody String content) {
        System.out.println("#New message [" + topic + "]:[" + content + "]");
    }

}
