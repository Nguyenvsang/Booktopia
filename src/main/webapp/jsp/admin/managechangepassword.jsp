<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đổi mật khẩu cho tài khoản</title>
    <%@ include file="headeradmin.jsp"%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 class="mt-3">Đổi mật khẩu cho tài khoản ${account.id}: ${account.username}</h1>
        
        <%-- Hiển thị thông báo nếu có --%>
        <c:if test="${not empty message}">
            <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'}">${message}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/managechangepassword?accountId=${account.id}" method="post">
            <div class="form-group">
                <label for="currentPassword">Mật khẩu hiện tại:</label>
                <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
            </div>
            
            <div class="form-group">
                <label for="newPassword">Mật khẩu mới:</label>
                <input type="password" class="form-control" id="newPassword" name="newPassword" required>
            </div>
            
            <div class="form-group">
                <label for="confirmPassword">Nhập lại mật khẩu mới:</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>
            
            <div class="text-center mb-4">
                <button type="submit" class="btn btn-primary">Xác nhận</button>
                <a href="${pageContext.request.contextPath}/managedetailaccount?accountId=${account.id}" class="btn btn-secondary">Thoát</a>
            </div>
        </form>
    </div>
</body>
<%@ include file="footeradmin.jsp" %>
</html>
