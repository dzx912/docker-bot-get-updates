package org.example.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс, который формирует запрос для отправки текстового сообщения в Telegram
 */
public class MessageRequest {

    @JsonProperty("chat_id")
    private Integer chatId;

    private String text;

    public MessageRequest() {
    }

    public MessageRequest(Integer chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
