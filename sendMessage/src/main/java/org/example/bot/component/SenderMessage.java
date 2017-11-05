package org.example.bot.component;

import org.example.bot.data.Message;
import org.example.bot.request.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Класс, который занимается отправкой сообщений в Telegram
 */
@Component
public class SenderMessage {
    private static final Logger log = LoggerFactory.getLogger(SenderMessage.class);

    @Value("${urlTelegramMain}")
    private String urlTelegramMain;

    @Value("${token}")
    private String token;

    private RestTemplate restTemplate;

    private String urlSendText;

    @PostConstruct
    private void setUp() {
        urlSendText = urlTelegramMain + "bot" + token + "/sendMessage";
        log.info("url sendText: " + urlSendText);

        restTemplate = new RestTemplate();
    }

    public Message send(MessageRequest request) {
        return restTemplate.postForObject(urlSendText, request, Message.class);
    }
}
