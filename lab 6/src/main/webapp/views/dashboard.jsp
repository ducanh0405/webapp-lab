<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; margin: 0; }
        .navbar { background: #007bff; color: white; padding: 15px; display: flex; justify-content: space-between; }
        .navbar h2 { margin: 0; font-size: 18px; }
        .navbar a { color: white; text-decoration: none; margin-left: 15px; }
        .navbar a:hover { text-decoration: underline; }
        .container { max-width: 800px; margin: 20px auto; background: white; padding: 20px; box-shadow: 0 0 5px rgba(0,0,0,0.1); }
        .role-badge { background: #ffc107; color: black; padding: 3px 8px; font-size: 12px; border-radius: 10px; font-weight: bold; }
        .stat-box { background: #e9ecef; padding: 15px; margin-top: 20px; border-left: 5px solid #007bff; }
        .btn { display: inline-block; background: #28a745; color: white; padding: 10px 15px; text-decoration: none; margin-top: 15px; border-radius: 3px; }
        .btn-primary { background: #007bff; }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>Student Management</h2>
        <div>
            <span>Welcome, ${sessionScope.fullName}</span>
            <span class="role-badge">${sessionScope.role}</span>
            <a href="dashboard">Dashboard</a>
            <a href="students">Students</a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <div class="container">
        <h1>Dashboard</h1>
        <p>Student information management system.</p>

        <div class="stat-box">
            <h3>Total students: <span style="color:#007bff;">${totalStudents != null ? totalStudents : 0}</span></h3>
        </div>

        <a href="students" class="btn">View Student List</a>
        
        <c:if test="${sessionScope.role eq 'admin'}">
            <a href="students?action=new" class="btn btn-primary">Add New Student</a>
        </c:if>
    </div>
</body>
</html>
