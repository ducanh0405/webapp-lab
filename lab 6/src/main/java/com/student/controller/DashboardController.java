package com.student.controller;

import com.student.dao.StudentDAO;
import com.student.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

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

        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}
