<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xem danh sách đơn hàng</title>
    <!-- Đường dẫn tới các tệp CSS của Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<%@ include file="../header.jsp"%>
</head>
<body>
    <div class="container">
        <h1>Xem danh sách đơn hàng</h1>
        
        <table class="table table-striped table-bordered">
            <tr>
                <th>Mã đơn hàng</th>
                <th>Ngày đặt</th>
                <th>Tổng tiền</th>
                <th>Trạng thái</th>
                <th>Tên</th>
                <th>Địa chỉ</th>
                <th>Số điện thoại</th>
                <th>Email</th>
                <th>Chi tiết</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.dateOrder}</td>
                    <td>${order.totalPrice}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.status == 0}">
                                <span class="badge badge-success">Chờ xác nhận</span>
                            </c:when>
                            <c:when test="${order.status == 1}">
                                <span class="badge badge-info">Chờ lấy hàng</span>
                            </c:when>
                            <c:when test="${order.status == 2}">
                                <span class="badge badge-primary">Đang giao</span>
                            </c:when>
                            <c:when test="${order.status == 3}">
                                <span class="badge badge-secondary">Đã giao</span>
                            </c:when>
                            <c:otherwise>Trạng thái không hợp lệ</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${order.name}</td>
                    <td>${order.address}</td>
                    <td>${order.phoneNumber}</td>
                    <td>${order.email}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/vieworderitems?orderId=${order.id}" class="btn btn-primary">Xem chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </div>
    <!-- Đường dẫn tới các tệp JavaScript của Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
<%@ include file="../footer.jsp"%>
</html>
