package repositories;

import org.springframework.jdbc.core.JdbcTemplate;

public class SimpleOperatorRepository implements OperatorRepository{
    JdbcTemplate jdbcTemplate;

    public SimpleOperatorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
