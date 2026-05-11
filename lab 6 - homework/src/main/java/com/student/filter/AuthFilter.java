package com.student.filter;

import com.student.dao.UserDAO;
import com.student.model.User;
import com.student.util.CookieUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Blocks unauthenticated access to protected resources. Supports "remember me" via DB token.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    private static final String ATTR_USER = "user";

    private UserDAO userDAO;

    @Override
    public void init(FilterConfig filterConfig) {
        userDAO = new UserDAO();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (path.isEmpty()) {
            path = "/";
        }

        if (isPublicPath(path)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        User user = session == null ? null : (User) session.getAttribute(ATTR_USER);

        if (user == null) {
            String token = CookieUtil.getCookieValue(req, "remember_token");
            if (token != null && !token.isBlank()) {
                user = userDAO.findUserByValidRememberToken(token.trim());
                if (user != null) {
                    HttpSession newSession = req.getSession(true);
                    newSession.setAttribute(ATTR_USER, user);
                    newSession.setAttribute("fullName", user.getFullName());
                    newSession.setAttribute("role", user.getRole());
                    newSession.setMaxInactiveInterval(30 * 60);
                }
            }
        }

        if (user == null) {
            String ctx = req.getContextPath();
            resp.sendRedirect(ctx + "/login");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        if (path.equals("/") || path.equals("/index.html") || path.equals("/demo-lab6-tasks.html")) {
            return true;
        }
        if (path.equals("/login") || path.equals("/logout") || path.equals("/theme")) {
            return true;
        }
        if (path.startsWith("/views/login.jsp")) {
            return true;
        }
        String lower = path.toLowerCase();
        if (lower.endsWith(".css") || lower.endsWith(".js") || lower.endsWith(".ico")
                || lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")
                || lower.endsWith(".gif") || lower.endsWith(".svg") || lower.endsWith(".woff2")) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
        // no-op
    }
}
