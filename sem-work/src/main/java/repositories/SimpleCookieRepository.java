package repositories;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public class SimpleCookieRepository implements CookieRepository{
    JdbcTemplate jdbcTemplate;

    public SimpleCookieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(UUID uuid, String userLogin) {
        jdbcTemplate.update("INSERT INTO cookie values (?, ?)", uuid.toString(), userLogin);
    }

    @Override
    public void delete(UUID uuid) {
        jdbcTemplate.update("DELETE FROM cookie WHERE uuid = ?", uuid.toString());
    }

    @Override
    public String findUserId(UUID uuid) {
        return jdbcTemplate.queryForObject("SELECT \"user\" FROM cookie WHERE uuid = ?", String.class, uuid.toString());
    }
}
