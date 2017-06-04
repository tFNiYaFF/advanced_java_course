package edu.technopolis.homework.messenger.messages;

/**
 * Created by Никита on 21.05.2017.
 */
public class LoginMessage extends Message {
    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginMessage{login="+login+";password="+password+"}";
    }
}
