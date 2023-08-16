<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật thông tin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<%@ include file="headeradmin.jsp"%>
</head>
<body>
    <div class="container">
        <h1>Nhập thông tin cần sửa</h1>
        
        <%-- Hiển thị thông báo nếu có --%>
        <c:if test="${not empty message}">
            <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'}">${message}</div>
        </c:if>
            
        <form action="${pageContext.request.contextPath}/editaccount?accountId=${account.id}" method="post" enctype="multipart/form-data">
            <div class="form-group">
	            <label for="id">Mã:</label>
	            <output class="form-control" id="id" name="id">${account.id}</output>
	        </div>
            <div class="form-group">
                <label for="firstName">Tên:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${account.firstName}" required>
            </div>
            <div class="form-group">
                <label for="lastName">Họ:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${account.lastName}" required>
            </div>
            <div class="form-group">
                <label for="username">Tên người dùng:</label>
                <input type="text" class="form-control" id="username" name="username" value="${account.username}" required>
            </div>
            <div class="form-group">
                <label for="gender">Giới tính:</label>
                <select class="form-control" id="gender" name="gender" required>
                    <option value="Nam" ${account.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                    <option value="Nữ" ${account.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                </select>
            </div>
            <div class="form-group">
                <label for="image">Đổi hình ảnh:</label>
                <input type="file" class="form-control-file" id="image" name="image">
            </div>
            <div class="form-group">
                <label for="dob">Ngày sinh:</label>
                <input type="date" class="form-control" id="dob" name="dob" value="${account.dateOfBirth}" required>
            </div>
            <div class="form-group">
                <label for="address">Địa chỉ:</label>
                <input type="text" class="form-control" id="address" name="address" value="${account.address}" required>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Số điện thoại:</label>
                <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value="${account.phoneNumber}" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${account.email}" required>
            </div>
            <div class="text-center mb-4">
                <button type="submit" class="btn btn-primary">Lưu</button>
                <a href="${pageContext.request.contextPath}/managedetailaccount?accountId=${account.id}" class="btn btn-secondary">Thoát</a>
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
<%@ include file="footeradmin.jsp"%>
</html>
