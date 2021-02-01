package repositories;

import models.Notification;

import java.util.List;

public interface NotificationRepository {
    List<Notification> getLast(int amount);
}
