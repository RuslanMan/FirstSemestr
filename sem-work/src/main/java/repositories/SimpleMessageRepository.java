package repositories;

import models.Message;
import models.Operator;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SimpleMessageRepository implements MessageRepository{
    JdbcTemplate jdbcTemplate;

    public static final RowMapper<Message> messageRowMapper = (rs, i) -> new Message(new User(rs.getString("user")),
            rs.getString("operator") == null ? null : new Operator(rs.getString("operator")),
            rs.getTimestamp("timestamp"),
            rs.getString("text"),
            rs.getBoolean("isWatched"));

    public SimpleMessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Message> getForOperator() {
        return jdbcTemplate.query("SELECT * FROM message WHERE \"isWatched\" = false AND operator is null order by timestamp", messageRowMapper);
    }

    @Override
    public List<Message> getForUser(String login) {
        return jdbcTemplate.query("SELECT * FROM message WHERE \"user\" = ? order by timestamp", messageRowMapper, login);
    }

    @Override
    public void saveReply(String text, String user, String login) {
        jdbcTemplate.update("UPDATE message SET \"isWatched\" = true WHERE \"isWatched\" = false AND \"user\" = ?", user);
        jdbcTemplate.update("INSERT INTO message values (?, ?, default, ?)", user, login, text);
    }

    @Override
    public void saveQuestion(String text, String login) {
        jdbcTemplate.update("INSERT INTO message values (?, null, default, ?)", login, text);
    }
}
