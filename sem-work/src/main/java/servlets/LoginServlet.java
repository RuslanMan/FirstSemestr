package servlets;

import managers.Page;
import managers.SecurityManager;
import managers.TemplateManager;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    TemplateManager templateManager;
    SecurityManager securityManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        templateManager = (TemplateManager) context.getAttribute("templateManager");
        securityManager = (SecurityManager) context.getAttribute("securityManager");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user == null) {
            templateManager.write(Page.login, resp, root);
            return;
        }
        root.put("user", user);
        templateManager.write(Page.profile, resp, root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user == null) {
            user = securityManager.login(req.getParameter("login"), req.getParameter("password"));
            if (user == null) {
                templateManager.write(Page.login, resp, root);
                return;
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        if ("true".equals(req.getParameter("remember"))) {
            resp.addCookie(securityManager.assignCookie(user));
        }
        root.put("user", user);
        templateManager.write(Page.profile, resp, root);
    }
}
