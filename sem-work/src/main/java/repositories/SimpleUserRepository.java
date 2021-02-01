package repositories;

import models.Operator;
import models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SimpleUserRepository implements UserRepository {
    JdbcTemplate jdbcTemplate;

    public static final RowMapper<User> userRowMapper = (rs, i) -> {
        if (rs.getBoolean("isOperator")) {
            return new Operator(rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("middleName"),
                    rs.getString("email"),
                    rs.getDate("birth"),
                    rs.getString("image"),
                    SimpleCardRepository.cardRowMapper.mapRow(rs, i));
        }
        return new User(rs.getString("login"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("middleName"),
                rs.getString("email"),
                rs.getDate("birth"),
                rs.getString("image"),
                SimpleCardRepository.cardRowMapper.mapRow(rs, i));
    };

    public SimpleUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(String s) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"user\", card WHERE login = ? AND \"user\".card = card.id", userRowMapper, s);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO \"user\" values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getMiddleName(),
                user.getEmail(),
                user.getBirth(),
                user.getImage(),
                user.getCard().getId());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE \"user\" SET name = ?, surname = ?, \"middleName\" = ?, birth = ?, image = ? WHERE login = ?",
                user.getName(),
                user.getSurname(),
                user.getMiddleName(),
                user.getBirth(),
                user.getImage(),
                user.getLogin());
    }
}
