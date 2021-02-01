package servlets;

import managers.Page;
import managers.TemplateManager;
import models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.CardRepository;
import repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    TemplateManager templateManager;
    UserRepository userRepository;
    CardRepository cardRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        templateManager = (TemplateManager) context.getAttribute("templateManager");
        userRepository = (UserRepository) context.getAttribute("userRepository");
        cardRepository = (CardRepository) context.getAttribute("cardRepository");
        passwordEncoder = (PasswordEncoder) context.getAttribute("passwordEncoder");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user != null) {
            root.put("user", user);
            templateManager.write(Page.profile, resp, root);
            return;
        }
        templateManager.write(Page.register, resp, root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        User user = (User) req.getAttribute("user");
        if (user != null) {
            root.put("user", user);
            templateManager.write(Page.profile, resp, root);
            return;
        }
        Date date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            date = new Date(format.parse(req.getParameter("birth")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!email.matches("^\\S+@\\S+$") || !password.matches("^(?=.*[0-9])(?=.*[a-z]).{8,32}$")) {
            templateManager.write(Page.register, resp, root);
            return;
        }
        user = new User(req.getParameter("login"),
                passwordEncoder.encode(password),
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("middleName"),
                email,
                date,
                null,
                cardRepository.findById(Long.parseLong(req.getParameter("card"))));
        userRepository.save(user);
        templateManager.write(Page.login, resp, root);
    }
}
