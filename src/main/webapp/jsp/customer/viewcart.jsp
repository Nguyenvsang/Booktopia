<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <%@ include file="../header.jsp"%>
</head>
<body>
    <div class="container-fluid mb-4">
        <h1>Giỏ hàng</h1>
        
        <!-- Hiển thị thông báo lỗi hoặc thành công -->
        <c:if test="${not empty message}">
            <div class="alert alert-${messageType}" role="alert">
                ${message}
            </div>
        </c:if>
        
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tên sách</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    <th>Tổng</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cartItem" items="${cartItems}">
                    <tr>
                        <td>${cartItem.id}</td>
                        <td>${cartItem.book.name}</td>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.book.price} vnd</td>
                        <td>${cartItem.quantity * cartItem.book.price} vnd</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/removefromcart?itemId=${cartItem.id}" class="btn btn-danger">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="text-right">
            <h4>Tổng số tiền: ${totalAmount} vnd</h4>
            <a href="${pageContext.request.contextPath}/checkout" class="btn btn-success">Thanh toán</a>
        </div>
        
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
<%@ include file="../footer.jsp"%>
</html>
