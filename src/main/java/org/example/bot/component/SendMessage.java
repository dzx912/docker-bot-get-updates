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
        boolean isMainChat = checkAndSetMainChat(request);
        if (isMainChat) {
            broadcastToAll(request);
        } else {
            forwardToMain(request);
            addToSetChatIds(request);
        }
    }

    private boolean checkAndSetMainChat(Message request) {
        Integer chatId = request.getFrom().getId();
        if (mainChatId == 0) {
            mainChatId = chatId;
        }

        return mainChatId == chatId;
    }

    private void broadcastToAll(Message request) {
        chatIds.stream()
                .map(id -> new MessageRequest(id, request.getText()))
                .forEach(message -> restTemplate.postForObject(urlSendText, message, Message.class));
    }

    private void forwardToMain(Message request) {
        ForwardMessageRequest forwardToLeaderRequest = generateMessage(request);
        restTemplate.postForObject(urlForwardMessage, forwardToLeaderRequest, Message.class);
    }

    private void addToSetChatIds(Message request) {
        chatIds.add(request.getFrom().getId());
    }

    private ForwardMessageRequest generateMessage(Message request) {
        Integer fromChatId = request.getFrom().getId();
        return new ForwardMessageRequest(mainChatId, fromChatId, request.getMessageId());
    }
}
