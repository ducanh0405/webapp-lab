package com.student.controller;

import com.student.dao.StudentDAO;
import com.student.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * StudentController - Front Controller (Servlet)
 * Điều hướng tất cả request liên quan đến Student
 */
@WebServlet("/students")
public class StudentController extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        // Lấy đường dẫn thực tế đến thư mục WEB-INF/data/students.json
        String dataFilePath = getServletContext().getRealPath("/WEB-INF/data/students.json");
        studentDAO = new StudentDAO(dataFilePath);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertStudent(request, response);
                break;
            case "update":
                updateStudent(request, response);
                break;
            default:
                listStudents(request, response);
        }
    }

    // Hiển thị danh sách sinh viên
    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/student-list.jsp").forward(request, response);
    }

    // Hiển thị form thêm mới
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("student", new Student());
        request.getRequestDispatcher("/student-form.jsp").forward(request, response);
    }

    // Hiển thị form chỉnh sửa
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/student-form.jsp").forward(request, response);
    }

    // Thêm sinh viên mới
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name  = request.getParameter("name");
        String email = request.getParameter("email");
        int age      = Integer.parseInt(request.getParameter("age"));

        Student student = new Student(0, name, email, age);
        studentDAO.addStudent(student);
        response.sendRedirect("students");
    }

    // Cập nhật sinh viên
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id       = Integer.parseInt(request.getParameter("id"));
        String name  = request.getParameter("name");
        String email = request.getParameter("email");
        int age      = Integer.parseInt(request.getParameter("age"));

        Student student = new Student(id, name, email, age);
        studentDAO.updateStudent(student);
        response.sendRedirect("students");
    }

    // Xóa sinh viên
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students");
    }
}
