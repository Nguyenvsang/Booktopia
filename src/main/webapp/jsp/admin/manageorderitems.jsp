<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xem chi tiết đơn hàng</title>
    <!-- Đường dẫn tới các tệp CSS của Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<%@ include file="headeradmin.jsp"%>
</head>
<body>
    <div class="container">
        <h1>Chi tiết đơn hàng</h1>
        <table class="table table-striped table-bordered">
            <tr>
                <th>Mã đơn hàng</th>
                <th>Ngày đặt</th>
                <th>Tổng tiền</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
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
	            <td>
				    <c:choose>
				        <c:when test="${order.status == 0 || order.status == 1  || order.status == 2}">
				            <form method="POST" action="${pageContext.request.contextPath}/updateorderstatus">
				                <input type="hidden" name="orderId" value="${order.id}">
				                <select class="form-control" name="status" onchange="this.form.submit()">
				                    <option value="0" <c:if test="${order.status == 0}">selected</c:if>>Chờ xác nhận</option>
				                    <option value="1" <c:if test="${order.status == 1}">selected</c:if>>Chờ lấy hàng</option>
				                    <option value="2" <c:if test="${order.status == 2}">selected</c:if>>Đang giao</option>
				                    <option value="3" <c:if test="${order.status == 3}">selected</c:if>>Đã giao</option>
				                </select>
				            </form>
				        </c:when>
				        <c:otherwise>
				            <!-- Hiển thị một dấu gạch ngang nếu trạng thái không hợp lệ -->
				            -
				        </c:otherwise>
				    </c:choose>
				</td>
            </tr>
        </table>
        
        <h3>Chi tiết vận chuyển:</h3>
        <p>Tên: ${order.name}</p>
        <p>Địa chỉ: ${order.address}</p>
        <p>Số điện thoại: ${order.phoneNumber}</p>
        <p>Email: ${order.email}</p>
        
        <h3>Những món hàng:</h3>
        <table class="table table-striped table-bordered">
            <tr>
                <th>Tên sách</th>
                <th>Số lượng</th>
                <th>Giá</th>
                <th>Tổng</th>
            </tr>
            <c:forEach var="orderItem" items="${orderItems}">
                <tr>
                    <td>${bookMap[orderItem.bookId].name}</td>
                    <td>${orderItem.quantity}</td>
                    <td>${bookMap[orderItem.bookId].price}</td>
                    <td>${orderItem.price}</td>
                </tr>
            </c:forEach>
        </table>
        
        <p class="text-center">
            <a href="${pageContext.request.contextPath}/manageorder" class="btn btn-primary">Trở lại</a>
        </p>
    </div>
    <!-- Đường dẫn tới các tệp JavaScript của Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
<%@ include file="footeradmin.jsp"%>
</html>
