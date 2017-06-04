package edu.technopolis.homework.messenger.messages;

import java.util.ArrayList;

/**
 * Created by Никита on 21.05.2017.
 */
public class ChatHistoryMessage extends Message {
    public long getChatId() {
        return chatId;
    }

    private long chatId;

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
