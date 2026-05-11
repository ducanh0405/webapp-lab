<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-theme="${uiTheme}">
<head>
    <meta charset="UTF-8">
    <title>Student List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f4f4f4; color: #222; }
        [data-theme="dark"] body { background: #151515; color: #eaeaea; }
        .wrap { padding: 24px 30px 40px; }
        h2 { color: #333; margin-top: 0; }
        [data-theme="dark"] h2 { color: #f0f0f0; }
        .app-navbar {
            background: #007bff; color: #fff; padding: 12px 20px;
            display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;
        }
        [data-theme="dark"] .app-navbar { background: #0d47a1; }
        .app-navbar a { color: #fff; text-decoration: none; margin-left: 10px; }
        .app-navbar a:hover { text-decoration: underline; }
        .nav-brand { font-weight: bold; }
        .nav-links { display: flex; flex-wrap: wrap; align-items: center; gap: 6px; }
        .nav-user { margin-right: 6px; }
        .role-badge { background: #ffc107; color: #222; padding: 2px 8px; border-radius: 10px; font-size: 12px; font-weight: bold; }
        .theme-toggle { opacity: 0.95; font-size: 13px; }
        table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        [data-theme="dark"] table { background: #1e1e1e; color: #eaeaea; }
        th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }
        [data-theme="dark"] th, [data-theme="dark"] td { border-color: #333; }
        th { background: #4a90e2; color: white; }
        [data-theme="dark"] th { background: #1565c0; }
        tr:hover { background: #f1f7ff; }
        [data-theme="dark"] tr:hover { background: #2a2a2a; }
        a.btn { padding: 6px 12px; border-radius: 4px; text-decoration: none; color: white; font-size: 13px; }
        .btn-add    { background: #28a745; display: inline-block; margin-bottom: 15px; }
        .btn-edit   { background: #ffc107; color: #333; }
        .btn-delete { background: #dc3545; }
        .alert-err { background: #f8d7da; color: #721c24; padding: 12px 16px; border-radius: 6px; margin-bottom: 16px; border: 1px solid #f5c6cb; }
        [data-theme="dark"] .alert-err { background: #3d1f24; color: #f8c9d0; border-color: #5c2a32; }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/includes/navbar.jspf"/>

<div class="wrap">
    <h2>Student List</h2>

    <c:if test="${not empty param.error}">
        <div class="alert-err"><c:out value="${param.error}"/></div>
    </c:if>

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
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td><c:out value="${student.name}"/></td>
                    <td><c:out value="${student.email}"/></td>
                    <td>${student.age}</td>
                    <td>
                        <c:if test="${sessionScope.role eq 'admin'}">
                            <a href="students?action=edit&amp;id=${student.id}" class="btn btn-edit">Edit</a>
                            &nbsp;
                            <a href="students?action=delete&amp;id=${student.id}"
                               class="btn btn-delete"
                               onclick="return confirm('Delete this student?')">Delete</a>
                        </c:if>
                        <c:if test="${sessionScope.role ne 'admin'}">
                            <span style="color:#888;">View only</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="/WEB-INF/includes/session-warning.jspf"/>
</body>
</html>
