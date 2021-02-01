package models;

import java.sql.Date;

public class Card {
    private long id;
    private long balance;
    private Date created;
    private Date expires;

    public Card(long id, long balance, Date created, Date expires) {
        this.id = id;
        this.balance = balance;
        this.created = created;
        this.expires = expires;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
