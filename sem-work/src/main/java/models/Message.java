package models;

import java.sql.Timestamp;

public class Message {
    User user;
    Operator operator;
    Timestamp timestamp;
    String text;
    boolean isWatched;

    public Message(User user, Operator operator, Timestamp timestamp, String text, boolean isWatched) {
        this.user = user;
        this.operator = operator;
        this.timestamp = timestamp;
        this.text = text;
        this.isWatched = isWatched;
    }

    public User getUser() {
        return user;
    }

    public Operator getOperator() {
        return operator;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public boolean isWatched() {
        return isWatched;
    }
}
