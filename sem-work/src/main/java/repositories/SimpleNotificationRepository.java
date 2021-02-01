package repositories;

import models.Notification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SimpleNotificationRepository implements NotificationRepository {
    JdbcTemplate jdbcTemplate;

    public static final RowMapper<Notification> notificationRowMapper = (rs, i) -> new Notification(rs.getInt("id"),
            rs.getDate("date"),
            rs.getString("title"),
            rs.getString("title"));

    public SimpleNotificationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Notification> getLast(int amount) {
        return jdbcTemplate.query("SELECT * FROM notification order by date desc limit ?", notificationRowMapper, amount);
    }
}
