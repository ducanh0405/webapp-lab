<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-theme="${uiTheme}">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; margin: 0; color: #222; }
        [data-theme="dark"] body { background: #151515; color: #eaeaea; }
        .app-navbar {
            background: #007bff; color: white; padding: 12px 20px;
            display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;
        }
        [data-theme="dark"] .app-navbar { background: #0d47a1; }
        .app-navbar a { color: white; text-decoration: none; margin-left: 10px; }
        .app-navbar a:hover { text-decoration: underline; }
        .nav-brand { font-weight: bold; }
        .nav-links { display: flex; flex-wrap: wrap; align-items: center; gap: 6px; }
        .nav-user { margin-right: 6px; }
        .role-badge { background: #ffc107; color: black; padding: 3px 8px; font-size: 12px; border-radius: 10px; font-weight: bold; }
        .theme-toggle { font-size: 13px; opacity: 0.95; }
        .container { max-width: 800px; margin: 20px auto; background: white; padding: 20px; box-shadow: 0 0 5px rgba(0,0,0,0.1); }
        [data-theme="dark"] .container { background: #1e1e1e; color: #eaeaea; }
        .stat-box { background: #e9ecef; padding: 15px; margin-top: 20px; border-left: 5px solid #007bff; }
        [data-theme="dark"] .stat-box { background: #2a2a2a; border-color: #42a5f5; }
        .cookie-stats { background: #e7f3ff; padding: 14px 16px; margin-top: 18px; border-radius: 6px; border: 1px solid #b8daff; }
        [data-theme="dark"] .cookie-stats { background: #1a2a3a; border-color: #2a4a6a; }
        .btn { display: inline-block; background: #28a745; color: white; padding: 10px 15px; text-decoration: none; margin-top: 15px; margin-right: 8px; border-radius: 3px; }
        .btn-primary { background: #007bff; }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/includes/navbar.jspf"/>

<div class="container">
    <h1>Dashboard</h1>
    <p>Student information management system.</p>

    <div class="stat-box">
        <h3>Total students: <span style="color:#007bff;">${totalStudents != null ? totalStudents : 0}</span></h3>
    </div>

    <div class="cookie-stats">
        <h4 style="margin-top:0;">Your statistics (cookies)</h4>
        <p style="margin:6px 0;"><strong>Total visits:</strong> <c:out value="${visitCount}"/></p>
        <p style="margin:6px 0;"><strong>Last login (this browser):</strong> <c:out value="${lastLoginCookieLabel}"/></p>
    </div>

    <a href="${pageContext.request.contextPath}/students" class="btn">View Student List</a>

    <c:if test="${sessionScope.role eq 'admin'}">
        <a href="${pageContext.request.contextPath}/students?action=new" class="btn btn-primary">Add New Student</a>
    </c:if>
</div>
<jsp:include page="/WEB-INF/includes/session-warning.jspf"/>
</body>
</html>
