package repositories;

import models.Card;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleCardRepository implements CardRepository{
    JdbcTemplate jdbcTemplate;

    public static final RowMapper<Card> cardRowMapper = (rs, i) -> new Card(rs.getInt("id"),
            rs.getInt("balance"),
            rs.getDate("created"),
            rs.getDate("expires"));

    public SimpleCardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Card findById(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM card where id = ?", cardRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
