<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin cá nhân</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <%@ include file="../header.jsp"%>
</head>
<body>
    <div class="container">
        <h1>Thông tin cá nhân</h1>
        
        <%-- Hiển thị thông báo nếu có --%>
        <c:if test="${not empty message}">
            <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'}">${message}</div>
        </c:if>
        
        <div class="form-group">
            <label for="firstName">Họ:</label>
            <output class="form-control" id="firstName" name="firstName">${account.firstName}</output>
        </div>
        <div class="form-group">
            <label for="lastName">Tên:</label>
            <output class="form-control" id="lastName" name="lastName">${account.lastName}</output>
        </div>
        <div class="form-group">
            <label for="username">Tên người dùng:</label>
            <output class="form-control" id="username" name="username">${account.username}</output>
        </div>
        <div class="form-group">
            <label for="password">Mật khẩu:</label>
                <a href="${pageContext.request.contextPath}/jsp/customer/changepassword.jsp" class="btn btn-danger">Đổi mật khẩu</a>
        </div>
        <div class="form-group">
            <label for="gender">Giới tính:</label>
            <output class="form-control" id="gender" name="gender">${account.gender}</output>
        </div>
        <div class="form-group">
            <label for="currentImage">Hình ảnh hiện tại:</label>
            <c:if test="${not empty sessionScope.account and not empty sessionScope.account.img}">
                <img src="${sessionScope.account.img}?v=${Math.random()}" class="img-thumbnail" id="currentImage" alt="Hình ảnh hiện tại" style="width: 200px; height: 200px;">
            </c:if>
            <c:if test="${empty sessionScope.account or empty sessionScope.account.img}">
                <img src="https://res.cloudinary.com/dosdzo1lg/image/upload/v1687862555/Booktopia/img_account/account_default.jpg" class="img-thumbnail" style="width: 200px; height: 200px;" id="currentImage" alt="Hình ảnh mặc định">
            </c:if>
        </div>
        <div class="form-group">
            <label for="dob">Ngày sinh:</label>
            <output class="form-control" id="dob" name="dob">${account.dateOfBirth}</output>
        </div>
        <div class="form-group">
            <label for="address">Địa chỉ:</label>
            <output class="form-control" id="address" name="address">${account.address}</output>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Số điện thoại:</label>
            <output class="form-control" id="phoneNumber" name="phoneNumber">${account.phoneNumber}</output>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <output class="form-control" id="email" name="email">${account.email}</output>
        </div>
        <div class="text-center mb-4">
            <a href="${pageContext.request.contextPath}/jsp/customer/updateaccount.jsp" class="btn btn-primary">Sửa thông tin</a>
        </div>
    </div>
    
</body>
<%@ include file="../footer.jsp" %>
</html>