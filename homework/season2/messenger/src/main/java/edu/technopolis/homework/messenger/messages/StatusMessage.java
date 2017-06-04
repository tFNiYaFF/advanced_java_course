package edu.technopolis.homework.messenger.messages;

/**
 * Created by Никита on 04.06.2017.
 */
public class StatusMessage extends Message {
    private boolean status;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
