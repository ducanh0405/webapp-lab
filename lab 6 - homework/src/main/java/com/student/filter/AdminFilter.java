package com.student.filter;

import com.student.model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * Restricts mutating student actions to admin role.
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = {"/students", "/student"})
public class AdminFilter implements Filter {

    private static final Set<String> ADMIN_ACTIONS = Set.of(
            "new", "insert", "edit", "update", "delete"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        action = action.trim().toLowerCase();

        if (!ADMIN_ACTIONS.contains(action)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");

        if (user == null) {
            String ctx = req.getContextPath();
            resp.sendRedirect(ctx + "/login");
            return;
        }

        if (!user.isAdmin()) {
            String ctx = req.getContextPath();
            String msg = "Access denied. Admin privileges required.";
            resp.sendRedirect(ctx + "/students?error=" + URLEncoder.encode(msg, StandardCharsets.UTF_8));
            return;
        }

        chain.doFilter(request, response);
    }
}
