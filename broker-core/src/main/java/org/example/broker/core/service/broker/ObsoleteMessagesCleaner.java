package org.example.broker.core.service.broker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.broker.core.repository.MessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ObsoleteMessagesCleaner {

    private final MessageRepository messageRepository;

    // Runs every minute
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void run() {
        log.debug("Started");
        messageRepository.deleteObsolete();
    }

}
