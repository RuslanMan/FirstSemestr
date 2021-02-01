package servlets;

import managers.Page;
import managers.TemplateManager;
import models.Message;
import models.Operator;
import models.User;
import repositories.ImageRepository;
import repositories.MessageRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageServlet extends HttpServlet {
    TemplateManager templateManager;
    MessageRepository messageRepository;
    ImageRepository imageRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        templateManager = (TemplateManager) context.getAttribute("templateManager");
        messageRepository = (MessageRepository) context.getAttribute("messageRepository");
        imageRepository = (ImageRepository) context.getAttribute("imageRepository");
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
        if (user instanceof Operator) {
            root.put("messages", messageRepository.getForOperator());
            templateManager.write(Page.chat, resp, root);
        } else {
            List<Message> list = messageRepository.getForUser(user.getLogin());
            root.put("messages", list);
            root.put("wait", list.size() > 0 && list.get(list.size() - 1).getOperator() != null);
            templateManager.write(Page.message, resp, root);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user == null) {
            templateManager.write(Page.login, resp, root);
            return;
        }

        if (user instanceof Operator) {
            messageRepository.saveReply(req.getParameter("text"), req.getParameter("user"), user.getLogin());
        } else {
            messageRepository.saveQuestion(req.getParameter("text"), user.getLogin());
        }

        resp.sendRedirect("/message");
    }
}
