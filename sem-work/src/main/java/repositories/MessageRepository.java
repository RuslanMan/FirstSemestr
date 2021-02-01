package repositories;

import models.Message;

import java.util.List;

public interface MessageRepository {
    List<Message> getForOperator();

    List<Message> getForUser(String login);

    void saveReply(String text, String user, String login);

    void saveQuestion(String text, String login);
}
