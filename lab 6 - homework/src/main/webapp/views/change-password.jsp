<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-theme="${uiTheme}">
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
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
        .wrap { max-width: 480px; margin: 24px auto; padding: 0 16px 40px; }
        .card {
            background: white; padding: 24px; border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }
        [data-theme="dark"] .card { background: #1e1e1e; }
        label { display: block; margin-top: 14px; font-weight: 600; color: #444; }
        [data-theme="dark"] label { color: #ccc; }
        input[type="password"] {
            width: 100%; padding: 10px; margin-top: 6px; box-sizing: border-box;
            border: 1px solid #ccc; border-radius: 4px;
        }
        [data-theme="dark"] input[type="password"] { background: #2a2a2a; border-color: #444; color: #eee; }
        button { margin-top: 20px; padding: 10px 18px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .msg-ok { background: #d4edda; color: #155724; padding: 12px; border-radius: 6px; margin-bottom: 14px; }
        .msg-err { background: #f8d7da; color: #721c24; padding: 12px; border-radius: 6px; margin-bottom: 14px; }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/includes/navbar.jspf"/>

<div class="wrap">
    <div class="card">
        <h2 style="margin-top:0;">Change password</h2>

        <c:if test="${not empty message}">
            <div class="msg-ok"><c:out value="${message}"/></div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="msg-err"><c:out value="${error}"/></div>
        </c:if>

        <form action="change-password" method="post">
            <label for="currentPassword">Current password</label>
            <input type="password" id="currentPassword" name="currentPassword" required autocomplete="current-password">

            <label for="newPassword">New password</label>
            <input type="password" id="newPassword" name="newPassword" required autocomplete="new-password" minlength="6">

            <label for="confirmPassword">Confirm new password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required autocomplete="new-password" minlength="6">

            <button type="submit">Update password</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/session-warning.jspf"/>
</body>
</html>
