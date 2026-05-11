package com.student.controller;

import com.student.dao.UserDAO;
import com.student.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/change-password")
public class ChangePasswordController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("themeRedirect", "/change-password");
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User sessionUser = session == null ? null : (User) session.getAttribute("user");
        if (sessionUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String current = request.getParameter("currentPassword");
        String newPass = request.getParameter("newPassword");
        String confirm = request.getParameter("confirmPassword");

        request.setAttribute("themeRedirect", "/change-password");

        if (current == null || newPass == null || confirm == null
                || current.isBlank() || newPass.isBlank() || confirm.isBlank()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        if (!newPass.equals(confirm)) {
            request.setAttribute("error", "New password and confirmation do not match.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        if (newPass.length() < 6) {
            request.setAttribute("error", "New password must be at least 6 characters.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        User dbUser = userDAO.getUserById(sessionUser.getId());
        if (dbUser == null || !BCrypt.checkpw(current, dbUser.getPassword())) {
            request.setAttribute("error", "Current password is incorrect.");
            request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
            return;
        }

        String hashed = BCrypt.hashpw(newPass, BCrypt.gensalt());
        if (userDAO.updatePassword(sessionUser.getId(), hashed)) {
            request.setAttribute("message", "Password updated successfully.");
        } else {
            request.setAttribute("error", "Could not update password. Please try again.");
        }
        request.getRequestDispatcher("/views/change-password.jsp").forward(request, response);
    }
}
