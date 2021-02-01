package repositories;

import models.User;

public interface UserRepository {
    User findById(String id);
    void save(User user);
    void update(User user);
}
