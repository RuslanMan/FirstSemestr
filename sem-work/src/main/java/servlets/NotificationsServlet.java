package servlets;

import managers.Page;
import managers.TemplateManager;
import models.User;
import repositories.NotificationRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NotificationsServlet extends HttpServlet {
    TemplateManager templateManager;
    NotificationRepository notificationRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        templateManager = (TemplateManager) context.getAttribute("templateManager");
        notificationRepository = (NotificationRepository) context.getAttribute("notificationRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        root.put("user", user);
        root.put("notifications", notificationRepository.getLast(10));
        templateManager.write(Page.notifications, resp, root);
    }
}
