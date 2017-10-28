package org.example.bot.response;

import java.util.List;

/**
 * Ответ, получаемый на getUpdates
 */
public class MessageResponse {

    private Boolean ok;
    private List<UpdateResultResponse> result;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<UpdateResultResponse> getResult() {
        return result;
    }

    public void setResult(List<UpdateResultResponse> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "ok=" + ok +
                ", result=" + result +
                '}';
    }
}
