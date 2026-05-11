package com.student.filter;

import com.student.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/student"})
public class AdminFilter implements Filter {

    // Admin-only actions
    private static final String[] ADMIN_ACTIONS = {
            "new",
            "insert",
            "edit",
            "update",
            "delete"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get action parameter
        String action = request.getParameter("action");

        // Check if action requires admin role
        if (isAdminAction(action)) {
            HttpSession session = httpRequest.getSession(false);
            User user = (session != null) ? (User) session.getAttribute("user") : null;

            // Check if user is admin
            if (user != null && user.isAdmin()) {
                // Allow access
                chain.doFilter(request, response);
            } else {
                // Deny access
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/student?error=Access+Denied.+Admin+role+required.");
            }
        } else {
            // Not an admin action, allow
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }

    private boolean isAdminAction(String action) {
        if (action == null) {
            return false;
        }
        for (String adminAction : ADMIN_ACTIONS) {
            if (action.equalsIgnoreCase(adminAction)) {
                return true;
            }
        }
        return false;
    }
}
