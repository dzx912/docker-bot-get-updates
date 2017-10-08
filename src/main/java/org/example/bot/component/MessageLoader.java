package org.example.bot.component;

import org.example.bot.response.MessageResponse;

/**
 * Общий интерфейс для получения от Telegram сообщений
 */
public interface MessageLoader {
    /**
     * Получить от сервера Telegram непрочитанные сообщения
     *
     * @param updateId идентификатор первого посылаемого сообщения.
     *                 Должен быть равен последний updateId + 1
     */
    MessageResponse take(int updateId);
}
