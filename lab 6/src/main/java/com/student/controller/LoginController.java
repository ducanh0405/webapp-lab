package com.student.controller;

import com.student.dao.UserDAO;
import com.student.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Already logged in, redirect to dashboard
            response.sendRedirect("dashboard");
            return;
        }
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Username and password are required");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            return;
        }

        // Authenticate user
        User user = userDAO.authenticate(username, password);
        
        if (user != null) {
            // 1. Invalidate old session (security)
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            
            // 2. Create new session
            HttpSession newSession = request.getSession(true);
            
            // 3. Store user data in session
            newSession.setAttribute("user", user);
            newSession.setAttribute("fullName", user.getFullName());
            newSession.setAttribute("role", user.getRole());
            
            // 4. Set session timeout (30 minutes)
            newSession.setMaxInactiveInterval(30 * 60);
            
            // 5. Redirect to dashboard
            response.sendRedirect("dashboard");
        } else {
            // Authentication failed
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
