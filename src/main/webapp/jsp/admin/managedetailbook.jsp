<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin sách</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<%@ include file="headeradmin.jsp"%>
</head>
<body>
    <div class="container-fluid mb-4">
        <h1>Thông tin sách</h1>
        
        <%-- Hiển thị thông báo nếu có --%>
        <c:if test="${not empty message}">
            <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'}">${message}</div>
        </c:if>
        
        <div class="card">
            <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="${book.img}" alt="Book Image" class="card-img">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                    	<div class="d-flex flex-grow-1">
					        <a href="${pageContext.request.contextPath}/updatebook?bookId=${book.id}" class="btn btn-success">Sửa thông tin</a>
					        <div class="ml-2">
					        	<a href="${pageContext.request.contextPath}/managebook" class="btn btn-primary">Thoát</a>
					        </div>
					    </div>
                    	
                    	<p class="card-text mt-4">Mã: ${book.id}</p>
                        <h5 class="card-title">Tên sách: ${book.name}</h5>
                        <p class="card-text">Tác giả: ${book.author}</p>
                        <p class="card-text">Giá: ${book.price} vnd</p>
                        <p class="card-text">Số lượng: ${book.quantity}</p>
                        
                        
                        <c:set var="categoryName" value=""/>
                        <c:forEach var="category" items="${categories}">
                            <c:if test="${category.id eq book.categoryId}">
                                <c:set var="categoryName" value="${category.name}"/>
                            </c:if>
                        </c:forEach>
                        <p class="card-text">Danh mục: ${categoryName}</p>
                        <p class="card-text">Mô tả:</p>
                        <pre class="card-text">${book.description}</pre>
                        <p class="card-text">Chi tiết:</p>
                        <pre class="card-text">${book.detail}</pre>
                        <c:choose>
                        	<c:when test="${book.status eq 1}">
                            	<div class="alert alert-success" role="alert">Đang kinh doanh</div>
						</c:when>
                        	<c:when test="${book.status eq 0}">
                            	<div class="alert alert-danger" role="alert">Ngừng kinh doanh</div>
                            </c:when>
                       	</c:choose>
                    </div>
                </div>
            </div>
        </div>
        
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
<%@ include file="footeradmin.jsp"%>
</html>