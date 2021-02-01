package repositories;

import models.User;

import java.util.UUID;

public interface CookieRepository {
    void save(UUID uuid, String userLogin);
    void delete(UUID uuid);
    String findUserId(UUID uuid);
}
