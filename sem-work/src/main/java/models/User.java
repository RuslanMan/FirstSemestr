package models;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private Date birth;
    private String image;
    private Card card;

    public User(String login) {
        this.login = login;
    }

    public User(String login, String password, String name, String surname, String middleName, String email, Date birth, String image, Card card) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.birth = birth;
        this.image = image;
        this.card = card;
    }

    public void editField(String name, String value) {
        switch (name) {
            case "name": {
                this.name = value;
                break;
            }
            case "surname": {
                this.surname = value;
                break;
            }
            case "middleName": {
                this.middleName = value;
                break;
            }
            case "birth": {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    this.birth = new Date(format.parse(value).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
