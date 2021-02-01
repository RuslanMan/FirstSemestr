package servlets;

import managers.Page;
import managers.TemplateManager;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeServlet extends HttpServlet {
    TemplateManager templateManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        templateManager = (TemplateManager) context.getAttribute("templateManager");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        if (!req.getRequestURI().equals("/")) {
            templateManager.write(Page.notFound, resp, root);
            return;
        }
        User user = (User) req.getAttribute("user");
        root.put("user", user);
        templateManager.write(Page.home, resp, root);
    }
}
