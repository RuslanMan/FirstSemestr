package servlets;

import managers.Page;
import managers.SecurityManager;
import managers.TemplateManager;
import models.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LogoutServlet extends HttpServlet {
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
        if (user != null) {
            for (Cookie cookie : req.getCookies()){
                if (cookie.getName().equals("login")) {
                    securityManager.removeCookie(UUID.fromString(cookie.getValue()));
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    break;
                }
            }

            HttpSession session = req.getSession(false);
            if (session != null) session.invalidate();
        }
        templateManager.write(Page.login, resp, root);
    }
}
