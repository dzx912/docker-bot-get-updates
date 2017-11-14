package org.example.bot.component;

import org.example.bot.request.MessageRequest;
import org.springframework.stereotype.Component;

/**
 * Компонент, который преобразовывает отправляемое сообщение.
 * Реализованна логика, что сообщение отправляется певрому написавшему (лидеру опроса)
 */
@Component
public class UserLeader {

    private int mainUser = 0;

    public MessageRequest generateMessage(MessageRequest request) {
        if (mainUser == 0) {
            mainUser = request.getChatId();
        }
        return new MessageRequest(mainUser, request.getText());
    }
}
