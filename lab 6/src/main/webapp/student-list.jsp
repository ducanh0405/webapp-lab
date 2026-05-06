<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Sinh Viên</title>
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
    </style>
</head>
<body>
    <h2>📋 Danh Sách Sinh Viên</h2>
    <a href="students?action=new" class="btn btn-add">+ Thêm Sinh Viên</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Họ Tên</th>
                <th>Email</th>
                <th>Tuổi</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <td>${student.age}</td>
                    <td>
                        <a href="students?action=edit&id=${student.id}" class="btn btn-edit">✏️ Sửa</a>
                        &nbsp;
                        <a href="students?action=delete&id=${student.id}"
                           class="btn btn-delete"
                           onclick="return confirm('Xóa sinh viên này?')">🗑️ Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
