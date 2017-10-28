package org.example.bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.bot.data.Message;

/**
 * Список сообщений, посылаемый в getUpdates, если статус = OK
 */
public class UpdateResultResponse {
    @JsonProperty("update_id")
    private Integer updateId;
    private Message message;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UpdateResultResponse{" +
                "updateId=" + updateId +
                ", message=" + message +
                '}';
    }
}
