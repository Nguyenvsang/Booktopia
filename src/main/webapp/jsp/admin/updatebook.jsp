<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật sách</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<%@ include file="headeradmin.jsp"%>
</head>
<body>
    <div class="container">
        <h1>Cập nhật sách</h1>
        
        <%-- Hiển thị thông báo nếu có --%>
        <c:if test="${not empty message}">
            <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'}">${message}</div>
        </c:if>
	        
        <form action="${pageContext.request.contextPath}/updatebook" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="id">Mã:</label>
                <input type="text" class="form-control" id="id" name="id" value="${book.id}" readonly>
            </div>
            <div class="form-group">
                <label for="name">Tên sách:</label>
                <input type="text" class="form-control" id="name" name="name" value="${book.name}" required>
            </div>
            <div class="form-group">
                <label for="author">Tác giả:</label>
                <input type="text" class="form-control" id="author" name="author" value="${book.author}" required>
            </div>
            <div class="form-group">
                <label for="price">Giá:</label>
                <input type="number" class="form-control" id="price" name="price" value="${book.price}" required>
            </div>
            <div class="form-group">
                <label for="category">Danh mục:</label>
                <select class="form-control" id="category" name="categoryId" required>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}" <c:if test="${category.id == book.categoryId}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="description">Mô tả:</label>
                <textarea class="form-control" id="description" name="description" rows="4" required>${book.description}</textarea>
            </div>
            <div class="form-group">
                <label for="status">Trạng thái:</label>
                <select class="form-control" id="status" name="status">
                    <option value="0" <c:if test="${book.status == 0}">selected</c:if>>Ngừng kinh doanh</option>
                    <option value="1" <c:if test="${book.status == 1}">selected</c:if>>Đang kinh doanh</option>
                </select>
            </div>
            <div class="form-group">
                <label for="detail">Chi tiết:</label>
                <textarea class="form-control" id="detail" name="detail" rows="4" required>${book.detail}</textarea>
            </div>
            <div class="form-group">
                <label for="quantity">Số lượng:</label>
                <input type="number" id="quantity" name="quantity" class="form-control" min="0" value="${book.quantity}">
            </div>
            <input type="hidden" name="img" value="${book.img}">

            <div class="form-group">
                <label for="image">Ảnh:</label>
                <input type="file" class="form-control-file" id="image" name="image" accept="image/*">
            </div>
            <div class="form-group">
                <img src="${book.img}" alt="Book Image" width="200"><br>
            </div>
            <div class="text-center mb-4">
                <button type="submit" class="btn btn-primary">Lưu</button>
                <a href="${pageContext.request.contextPath}/managedetailbook?bookId=${book.id}" class="btn btn-secondary">Thoát</a>
            </div>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
<%@ include file="footeradmin.jsp"%>
</html>
