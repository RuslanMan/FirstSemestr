package managers;

import models.User;

import javax.servlet.http.Cookie;
import java.util.UUID;

public interface SecurityManager {
    User login(String login, String password);
    Cookie assignCookie(User user);
    void removeCookie(UUID uuid);
    User checkCookie(UUID uuid);
}
