package managers;

import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.CookieRepository;
import repositories.UserRepository;

import javax.servlet.http.Cookie;
import java.util.UUID;

public class SimpleSecurityManager implements SecurityManager {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    CookieRepository cookieRepository;
    JdbcTemplate jdbcTemplate;

    public SimpleSecurityManager(PasswordEncoder passwordEncoder, UserRepository userRepository, CookieRepository cookieRepository, JdbcTemplate jdbcTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.cookieRepository = cookieRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User login(String login, String password) {
        User user = userRepository.findById(login);
        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        return null;
    }

    @Override
    public Cookie assignCookie(User user) {
        UUID uuid = UUID.randomUUID();
        cookieRepository.save(uuid, user.getLogin());
        Cookie cookie = new Cookie("login", uuid.toString());
        cookie.setMaxAge(7776000);
        return cookie;
    }

    @Override
    public void removeCookie(UUID uuid) {
        cookieRepository.delete(uuid);
    }

    @Override
    public User checkCookie(UUID uuid) {
        return userRepository.findById(cookieRepository.findUserId(uuid));
    }
}
