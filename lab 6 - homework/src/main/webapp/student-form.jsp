<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-theme="${uiTheme}">
<head>
    <meta charset="UTF-8">
    <title>${empty student.id || student.id == 0 ? 'Add Student' : 'Edit Student'}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f4f4f4; color: #222; }
        [data-theme="dark"] body { background: #151515; color: #eaeaea; }
        .app-navbar {
            background: #007bff; color: #fff; padding: 12px 20px;
            display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;
        }
        [data-theme="dark"] .app-navbar { background: #0d47a1; }
        .app-navbar a { color: #fff; text-decoration: none; margin-left: 10px; }
        .nav-brand { font-weight: bold; }
        .nav-links { display: flex; flex-wrap: wrap; align-items: center; gap: 6px; }
        .role-badge { background: #ffc107; color: #222; padding: 2px 8px; border-radius: 10px; font-size: 12px; font-weight: bold; }
        .wrap { padding: 24px 30px; }
        h2 { color: #333; }
        [data-theme="dark"] h2 { color: #f0f0f0; }
        .form-container {
            background: white; padding: 25px; border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1); max-width: 500px;
        }
        [data-theme="dark"] .form-container { background: #1e1e1e; }
        label { display: block; margin-top: 15px; font-weight: bold; color: #555; }
        [data-theme="dark"] label { color: #bbb; }
        input[type="text"], input[type="email"], input[type="number"] {
            width: 100%; padding: 9px; margin-top: 5px; border: 1px solid #ccc;
            border-radius: 4px; box-sizing: border-box; font-size: 14px;
        }
        [data-theme="dark"] input[type="text"], [data-theme="dark"] input[type="email"], [data-theme="dark"] input[type="number"] {
            background: #2a2a2a; border-color: #444; color: #eee;
        }
        .btn-submit { margin-top: 20px; padding: 10px 20px; background: #4a90e2; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
        .btn-back { display: inline-block; margin-top: 10px; color: #4a90e2; text-decoration: none; }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/includes/navbar.jspf"/>

<div class="wrap">
    <h2>${empty student.id || student.id == 0 ? 'Add New Student' : 'Edit Student'}</h2>

    <div class="form-container">
        <form action="students" method="post">
            <c:choose>
                <c:when test="${empty student.id || student.id == 0}">
                    <input type="hidden" name="action" value="insert">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="${student.id}">
                </c:otherwise>
            </c:choose>

            <label>Full Name</label>
            <input type="text" name="name" value="${student.name}" required placeholder="Enter full name...">

            <label>Email</label>
            <input type="email" name="email" value="${student.email}" required placeholder="Enter email...">

            <label>Age</label>
            <input type="number" name="age" value="${student.age}" required min="1" max="100" placeholder="Enter age...">

            <br>
            <button type="submit" class="btn-submit">Save</button>
        </form>
        <a href="students" class="btn-back">Back to list</a>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/session-warning.jspf"/>
</body>
</html>
