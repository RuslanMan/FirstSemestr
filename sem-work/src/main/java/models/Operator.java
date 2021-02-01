package models;

import java.sql.Date;

public class Operator extends User{
    boolean isOperator = true;

    public Operator(String login) {
        super(login);
    }

    public Operator(String login, String password, String name, String surname, String middleName, String email, Date birth, String image, Card card) {
        super(login, password, name, surname, middleName, email, birth, image, card);
    }

    public boolean isOperator() {
        return isOperator;
    }
}
