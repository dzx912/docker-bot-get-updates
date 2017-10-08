package org.example.bot.scheduler;

import org.example.bot.component.MessageLoader;
import org.example.bot.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.example.bot.helper.AnalyzeMessageHelper.getLastUpdateId;

/**
 * Класс, который с определенной переодичностью получает не прочитанные сообщения
 * от сервера Telegram
 */
@Component
public class GetUpdatesScheduler {
    private static final Logger log = LoggerFactory.getLogger(GetUpdatesScheduler.class);

    private int currentMessageId;

    @Autowired
    private MessageLoader loader;

    @PostConstruct
    private void setUp() {
        currentMessageId = 0;
    }

    @Scheduled(fixedRate = 5_000)
    public void getUpdate() {
        MessageResponse messages = loader.take(currentMessageId);

        currentMessageId = getLastUpdateId(messages) + 1;

        log.info("messages = " + messages);
    }
}