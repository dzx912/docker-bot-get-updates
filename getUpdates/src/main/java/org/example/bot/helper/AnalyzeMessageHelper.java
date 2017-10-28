package org.example.bot.helper;

import org.example.bot.response.MessageResponse;
import org.example.bot.response.UpdateResultResponse;

import java.util.Comparator;

/**
 * Класс, в котором собраны методы для помощи анализа полученных сообщений
 */
public class AnalyzeMessageHelper {

    private AnalyzeMessageHelper() {
    }

    public static int getLastUpdateId(MessageResponse messages) {
        return messages.getResult()
                .stream()
                .map(UpdateResultResponse::getUpdateId)
                .max(Comparator.comparing(Integer::valueOf))
                .orElse(-1);
    }
}
