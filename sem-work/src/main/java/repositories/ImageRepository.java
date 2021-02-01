package repositories;

import javax.servlet.http.Part;

public interface ImageRepository {
    String saveForUser(Part part, String login);
}
