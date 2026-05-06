<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Student Management</title>
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center;
               align-items: center; height: 100vh; margin: 0; background: #f4f4f4; }
        .card { background: white; padding: 40px; border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.1); text-align: center; }
        h1 { color: #4a90e2; }
        a  { display: inline-block; margin-top: 20px; padding: 12px 25px;
             background: #4a90e2; color: white; text-decoration: none; border-radius: 6px; }
        a:hover { background: #357abd; }
    </style>
</head>
<body>
    <div class="card">
        <h1>🎓 Student Management</h1>
        <p>Chào mừng đến với hệ thống quản lý sinh viên - Lab 5 MVC</p>
        <a href="students">📋 Xem danh sách sinh viên</a>
    </div>
</body>
</html>
