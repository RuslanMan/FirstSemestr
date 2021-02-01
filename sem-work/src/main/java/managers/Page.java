package managers;

import freemarker.template.Template;
import listeners.ContextListener;

import java.io.IOException;

public enum Page {
    chat("chat.ftlh"),
    home("home.ftlh"),
    login("login.ftlh"),
    message("message.ftlh"),
    notFound("notFound.ftlh"),
    notifications("notifications.ftlh"),
    profile("profile.ftlh"),
    register("register.ftlh");

    public final Template template;

    Page(String filename) {
        try {
            this.template = ContextListener.cfg.getTemplate(filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
