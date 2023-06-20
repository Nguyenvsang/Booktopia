<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng kí tài khoản</title>
<%@ include file="../header.jsp"%>
</head>
<body>
<div class="container mt-4 mb-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="text-center">Đăng kí nào!</h1>
            <%-- Hiển thị thông báo nếu có --%>
            <% String message = (String) request.getAttribute("message"); %>
            <% if (message != null) { %>
                <div class="alert alert-info" role="alert">
                    ${message}
                </div>
            <% } %>
            <form action="${pageContext.request.contextPath}/registeraccount" method="post">
                <div class="form-group">
                    <label for="username">Tên tài khoản:</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="address">Địa chỉ:</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Số điện thoại:</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
                </div>
                <div class="form-group">
                    <label for="firstName">Tên:</label>
                    <input type="text" class="form-control" id="firstName" name="firstName">
                </div>
                <div class="form-group">
                    <label for="lastName">Họ:</label>
                    <input type="text" class="form-control" id="lastName" name="lastName">
                </div>
                <div class="form-group">
                    <label for="gender">Giới tính:</label>
                    <select class="form-control" id="gender" name="gender">
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="dob">Ngày sinh:</label>
                    <input type="date" class="form-control" id="dob" name="dob" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Đăng kí</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp"%>
</html>