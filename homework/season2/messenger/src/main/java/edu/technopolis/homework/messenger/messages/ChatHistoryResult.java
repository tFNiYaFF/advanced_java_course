package edu.technopolis.homework.messenger.messages;

import java.util.ArrayList;

/**
 * Created by Никита on 04.06.2017.
 */
public class ChatHistoryResult extends Message {

    private ArrayList<TextMessage> messages;

    public ArrayList<TextMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<TextMessage> messages) {
        this.messages = messages;
    }
}
