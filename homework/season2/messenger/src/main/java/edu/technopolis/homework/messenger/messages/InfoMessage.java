package edu.technopolis.homework.messenger.messages;

/**
 * Created by Никита on 21.05.2017.
 */
public class InfoMessage extends Message {
        private long userInfoId;

    public long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public String toString() {
            return "InfoMessage{login="+userInfoId+"}";
        }
}
