<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<%@ include file="headeradmin.jsp"%>
</head>
<body>
    <div class="container">
        <h1>Thêm danh mục</h1>
        
        <%-- Hiển thị thông báo nếu có --%>
        <c:if test="${not empty message}">
            <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'}">${message}</div>
        </c:if>
	        
        <form action="${pageContext.request.contextPath}/addcategory" method="POST">
            <div class="form-group">
                <label for="name">Tên danh mục:</label>
                <input type="text" class="form-control" id="name" name="name" value="${category.name}" required>
            </div>
            
            <div class="form-group">
                <label for="status">Trạng thái:</label>
                <select class="form-control" id="status" name="status">
                    <option value="0">Ngừng kinh doanh</option>
                    <option value="1">Đang kinh doanh</option>
                </select>
            </div>
            <div class="text-center mb-4">
                <button type="submit" class="btn btn-primary">Tạo mới</button>
                <a href="${pageContext.request.contextPath}/managecategory" class="btn btn-secondary">Thoát</a>
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
<%@ include file="footeradmin.jsp"%>
</html>
