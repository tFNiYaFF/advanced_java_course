package edu.technopolis.homework.messenger.messages;

import java.util.HashSet;

/**
 * Created by Никита on 21.05.2017.
 */
public class ChatCreateMessage extends Message {
    private HashSet<Long> users=new HashSet<>();

    public HashSet<Long> getUsers() {
        return users;
    }

    public void add(long element){
        users.add(element);
    }
}
