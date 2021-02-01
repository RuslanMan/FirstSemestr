package models;

import java.sql.Date;

public class Notification {
    private long id;
    private Date date;
    private String title;
    private String text;

    public Notification(long id, Date date, String title, String text) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
