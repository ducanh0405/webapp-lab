<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${empty student.id || student.id == 0 ? 'Thêm Sinh Viên' : 'Sửa Sinh Viên'}</title>
    <style>
        body  { font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }
        h2    { color: #333; }
        .form-container {
            background: white; padding: 25px; border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1); max-width: 500px;
        }
        label  { display: block; margin-top: 15px; font-weight: bold; color: #555; }
        input[type="text"], input[type="email"], input[type="number"] {
            width: 100%; padding: 9px; margin-top: 5px; border: 1px solid #ccc;
            border-radius: 4px; box-sizing: border-box; font-size: 14px;
        }
        .btn-submit { margin-top: 20px; padding: 10px 20px; background: #4a90e2; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 14px; }
        .btn-submit:hover { background: #357abd; }
        .btn-back   { display: inline-block; margin-top: 10px; color: #4a90e2; text-decoration: none; }
    </style>
</head>
<body>
    <h2>${empty student.id || student.id == 0 ? '➕ Thêm Sinh Viên Mới' : '✏️ Sửa Thông Tin Sinh Viên'}</h2>

    <div class="form-container">
        <form action="students" method="post">
            <!-- Ẩn action: insert hoặc update -->
            <c:choose>
                <c:when test="${empty student.id || student.id == 0}">
                    <input type="hidden" name="action" value="insert">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id"     value="${student.id}">
                </c:otherwise>
            </c:choose>

            <label>Họ và Tên</label>
            <input type="text" name="name" value="${student.name}" required placeholder="Nhập họ tên...">

            <label>Email</label>
            <input type="email" name="email" value="${student.email}" required placeholder="Nhập email...">

            <label>Tuổi</label>
            <input type="number" name="age" value="${student.age}" required min="1" max="100" placeholder="Nhập tuổi...">

            <br>
            <button type="submit" class="btn-submit">💾 Lưu</button>
        </form>
        <a href="students" class="btn-back">← Quay lại danh sách</a>
    </div>
</body>
</html>
