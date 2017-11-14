package org.example.bot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Anton Lenok <AILenok.SBT@sberbank.ru>
 * @since 13.11.17.
 */
public class ForwardMessageRequest {

    @JsonProperty("chat_id")
    private Integer chatId;

    @JsonProperty("from_chat_id")
    private Integer fromChatId;

    @JsonProperty("message_id")
    private Integer messageId;

    public ForwardMessageRequest() {
    }

    public ForwardMessageRequest(Integer chatId, Integer fromChatId, Integer messageId) {
        this.chatId = chatId;
        this.fromChatId = fromChatId;
        this.messageId = messageId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getFromChatId() {
        return fromChatId;
    }

    public void setFromChatId(Integer fromChatId) {
        this.fromChatId = fromChatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
