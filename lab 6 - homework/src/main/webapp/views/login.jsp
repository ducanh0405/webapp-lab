<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" data-theme="${uiTheme}">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; color: #222; }
        [data-theme="dark"] body { background: #151515; color: #eaeaea; }
        .container { background: white; padding: 30px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.2); width: 320px; }
        [data-theme="dark"] .container { background: #1e1e1e; }
        h1 { font-size: 20px; text-align: center; color: #333; margin-top: 0; }
        [data-theme="dark"] h1 { color: #f0f0f0; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #666; }
        [data-theme="dark"] label { color: #bbb; }
        input[type="text"], input[type="password"] { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 3px; }
        [data-theme="dark"] input[type="text"], [data-theme="dark"] input[type="password"] { background: #2a2a2a; border-color: #444; color: #eee; }
        button { width: 100%; padding: 10px; background: #007bff; color: white; border: none; border-radius: 3px; cursor: pointer; }
        button:hover { background: #0056b3; }
        .alert { padding: 10px; margin-bottom: 15px; font-size: 14px; border-radius: 3px; }
        .alert-error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .alert-success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .demo-box { margin-top: 20px; font-size: 13px; color: #555; background: #eee; padding: 10px; border-radius: 4px; }
        [data-theme="dark"] .demo-box { background: #2a2a2a; color: #ccc; }
        .chk { display: flex; align-items: center; gap: 8px; margin: 12px 0; font-size: 14px; }
        .theme-bar { text-align: center; margin-bottom: 12px; font-size: 13px; }
        .theme-bar a { color: #007bff; }
    </style>
</head>
<body>
<div class="container">
    <div class="theme-bar">
        <c:url var="tl" value="/theme"><c:param name="set" value="light"/><c:param name="redirect" value="/login"/></c:url>
        <c:url var="td" value="/theme"><c:param name="set" value="dark"/><c:param name="redirect" value="/login"/></c:url>
        <a href="${tl}">Light</a> &middot; <a href="${td}">Dark</a>
    </div>
    <h1>System Login</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-error"><c:out value="${error}"/></div>
    </c:if>

    <c:if test="${not empty param.message}">
        <div class="alert alert-success"><c:out value="${param.message}"/></div>
    </c:if>

    <form action="login" method="post">
        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" value="${param.username}" required>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required>
        </div>
        <div class="chk">
            <input type="checkbox" name="remember_me" id="remember_me" value="on">
            <label for="remember_me" style="margin:0;">Remember me (30 days)</label>
        </div>
        <button type="submit">Login</button>
    </form>

    <div class="demo-box">
        <b>Demo Accounts:</b><br>
        - Admin: admin / password123<br>
        - User: john / password123
    </div>
</div>
</body>
</html>
