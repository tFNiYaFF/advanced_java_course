package edu.technopolis.homework.messenger;

/**
 *
 */
public class User {
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public User(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
