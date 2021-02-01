package repositories;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class SimpleImageRepository implements ImageRepository{
    String resourcePath;

    public SimpleImageRepository(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String saveForUser(Part part, String login) {
        if (part.getSize() != 0) {
            String sfn = part.getSubmittedFileName();
            String extension = sfn.substring(sfn.lastIndexOf('.'));
            String fullName = resourcePath + "images/" + login;
            File file = new File(fullName);
            file.mkdirs();
            fullName += "/avatar" + extension;
            try {
                part.write(fullName);
                return "images/" + login + "/avatar" + extension;
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return null;
    }
}
