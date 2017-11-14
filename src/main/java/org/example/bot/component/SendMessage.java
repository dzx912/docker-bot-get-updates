package org.example.bot.component;

import org.example.bot.data.Message;
import org.example.bot.request.ForwardMessageRequest;
import org.example.bot.request.MessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton Lenok <AILenok.SBT@sberbank.ru>
 * @since 13.11.17.
 */
@Component
public class SendMessage {

    @Value("${urlTelegramMain}")
    private String urlTelegramMain;

    @Value("${token}")
    private String token;

    private int mainChatId = 0;
    private Set<Integer> chatIds;

    private String urlSendText;
    private String urlForwardMessage;

    private RestTemplate restTemplate;

    @PostConstruct
    private void setUp() {
        restTemplate = new RestTemplate();
        chatIds = new HashSet<>();

        urlSendText = urlTelegramMain + "bot" + token + "/sendMessage";
        urlForwardMessage = urlTelegramMain + "bot" + token + "/forwardMessage";
    }

    public void send(Message request) {
        if (isMainChat(request)) {
            forwardToMain(request);
        } else {
            broadcastToAll(request);
        }
    }

    private void forwardToMain(Message request) {
        ForwardMessageRequest forwardToLeaderRequest = generateMessage(request);
        restTemplate.postForObject(urlForwardMessage, forwardToLeaderRequest, Message.class);
    }

    private void broadcastToAll(Message request) {
        chatIds.add(request.getFrom().getId());
        chatIds.stream()
                .map(id -> new MessageRequest(id, request.getText()))
                .forEach(message -> restTemplate.postForObject(urlSendText, message, Message.class));
    }

    private boolean isMainChat(Message request) {
        return mainChatId != 0 && mainChatId == request.getFrom().getId();
    }

    private ForwardMessageRequest generateMessage(Message request) {
        Integer fromChatId = request.getFrom().getId();
        if (mainChatId == 0) {
            mainChatId = fromChatId;
        }
        return new ForwardMessageRequest(mainChatId, fromChatId, request.getMessageId());
    }
}
