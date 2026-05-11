<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }
        h2   { color: #333; }
        table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: #4a90e2; color: white; }
        tr:hover { background: #f1f7ff; }
        a.btn { padding: 6px 12px; border-radius: 4px; text-decoration: none; color: white; font-size: 13px; }
        .btn-add    { background: #28a745; display: inline-block; margin-bottom: 15px; }
        .btn-edit   { background: #ffc107; color: #333; }
        .btn-delete { background: #dc3545; }
        .navbar { background: #333; color: white; padding: 10px 20px; display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; border-radius: 5px; }
        .navbar h2 { color: white; margin: 0; }
        .navbar a { color: white; text-decoration: none; margin-left: 15px; }
        .navbar a:hover { text-decoration: underline; }
        .role-badge { background: #17a2b8; padding: 3px 8px; border-radius: 3px; font-size: 12px; margin-left: 10px; }
        .role-admin { background: #dc3545; }
        .alert { padding: 10px; border-radius: 5px; margin-bottom: 15px; }
        .alert-error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>📋 Student Management System</h2>
        <div class="navbar-right" style="display: flex; align-items: center;">
            <div class="user-info">
                <span>Welcome, ${sessionScope.fullName}</span>
                <span class="role-badge role-${sessionScope.role}">
                    ${sessionScope.role}
                </span>
            </div>
            <a href="dashboard">Dashboard</a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <c:if test="${not empty param.error}">
        <div class="alert alert-error">
            ${param.error}
        </div>
    </c:if>

    <h2>📋 Student List</h2>
    <c:if test="${sessionScope.role eq 'admin'}">
        <a href="students?action=new" class="btn btn-add">+ Add Student</a>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Age</th>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <th>Actions</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <td>${student.age}</td>
                    <c:if test="${sessionScope.role eq 'admin'}">
                        <td>
                            <a href="students?action=edit&id=${student.id}" class="btn btn-edit">✏️ Edit</a>
                            &nbsp;
                            <a href="students?action=delete&id=${student.id}"
                               class="btn btn-delete"
                               onclick="return confirm('Delete this student?')">🗑️ Delete</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
