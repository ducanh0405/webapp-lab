package com.student.controller;

import com.student.dao.UserDAO;
import com.student.model.User;
import com.student.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");
        String remember = CookieUtil.getCookieValue(request, "remember_token");
        if (remember != null && !remember.isBlank()) {
            userDAO.deleteRememberToken(remember);
        }
        if (user != null) {
            userDAO.deleteRememberTokensForUser(user.getId());
        }
        String path = CookieUtil.appCookiePath(request);
        CookieUtil.deleteCookie(response, "remember_token", path);

        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login?message=You have been logged out successfully");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
