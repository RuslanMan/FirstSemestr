package filters;

import managers.SecurityManager;
import models.User;
import repositories.UserRepository;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class LoginFilter implements Filter {
    SecurityManager securityManager;
    UserRepository userRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        securityManager = (SecurityManager) context.getAttribute("securityManager");
        userRepository = (UserRepository) context.getAttribute("userRepository");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                request.setAttribute("user", user);
            }
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("login")) {
                        User user = securityManager.checkCookie(UUID.fromString(cookie.getValue()));
                        request.setAttribute("user", user);
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
