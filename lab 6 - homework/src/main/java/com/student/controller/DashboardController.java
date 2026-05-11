package com.student.controller;

import com.student.dao.StudentDAO;
import com.student.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        String dataFilePath = getServletContext().getRealPath("/WEB-INF/data/students.json");
        studentDAO = new StudentDAO(dataFilePath);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            int totalStudents = studentDAO.getAllStudents().size();
            request.setAttribute("totalStudents", totalStudents);
        } catch (Exception e) {
            request.setAttribute("totalStudents", 0);
        }

        int visitCount = CookieUtil.getIntCookie(request, "visit_count", 0);
        request.setAttribute("visitCount", visitCount);
        long lastMs = CookieUtil.getLongCookie(request, "last_login", 0L);
        String lastLoginLabel = "First visit";
        if (lastMs > 0) {
            DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    .withLocale(Locale.getDefault())
                    .withZone(ZoneId.systemDefault());
            lastLoginLabel = fmt.format(Instant.ofEpochMilli(lastMs));
        }
        request.setAttribute("lastLoginCookieLabel", lastLoginLabel);
        request.setAttribute("themeRedirect", "/dashboard");

        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}
