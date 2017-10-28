package org.example.bot.component;

import org.example.bot.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Класс, который непосредственно вызывает getUpdates
 */
@Component
public class GetUpdates implements MessageLoader {
    private static final Logger log = LoggerFactory.getLogger(GetUpdates.class);


    @Value("${urlTelegramMain}")
    private String urlTelegramMain;

    @Value("${token}")
    private String token;

    private RestTemplate restTemplate;

    private String urlGetUpdates;

    @PostConstruct
    private void setUp() {
        urlGetUpdates = urlTelegramMain + "bot" + token + "/getUpdates";
        log.info("url getUpdates: " + urlGetUpdates);

        restTemplate = new RestTemplate();
    }

    public MessageResponse take(int updateId) {
        String url = urlGetUpdates + "?offset=" + updateId;

        return restTemplate.getForObject(url, MessageResponse.class);
    }
}
