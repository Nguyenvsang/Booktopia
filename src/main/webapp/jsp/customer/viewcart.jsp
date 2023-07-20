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
        
        <%-- Hiển thị thông báo nếu có --%>
        <c:if test="${not empty message}">
            <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'}">${message}</div>
        </c:if>
        
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th class="align-middle text-center">Ảnh</th>
                    <th class="align-middle text-center">Tên sách</th>
                    <th class="align-middle text-center">Số lượng</th>
                    <th class="align-middle text-center">Giá (vnd)</th>
                    <th class="align-middle text-center">Tổng (vnd)</th>
                    <th class="align-middle text-center">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cartItem" items="${cartItems}">
                    <tr>
                        <td class="text-center text-center"><img src="${bookMap[cartItem.bookId].img}" width="100"></td>
                        <td class="align-middle text-center">${bookMap[cartItem.bookId].name}</td>
                        <td class="align-middle text-center">
                            <div class="input-group justify-content-center">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-secondary" type="button" onclick="decreaseQuantity(${cartItem.id})">-</button>
                                </div>
                                <div class="col-auto">
                                	<input type="text" class="form-control" value="${cartItem.quantity}" id="quantity_${cartItem.id}" readonly>
                                </div>
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="button" onclick="increaseQuantity(${cartItem.id}, ${bookMap[cartItem.bookId].quantity})">+</button>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle text-center">${bookMap[cartItem.bookId].price}</td>
                        <td class="align-middle text-center" id="totalpriceitem_${cartItem.id}">${cartItem.quantity * bookMap[cartItem.bookId].price}</td>
                        <td class="align-middle text-center">
                            <a href="${pageContext.request.contextPath}/removefromcart?itemId=${cartItem.id}" class="btn btn-danger">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div class="text-right">
        	<h4 >Tổng số tiền (vnd) </h4>
            <h4 id="totalpricecart">${totalAmount}</h4>
            <a href="${pageContext.request.contextPath}/orderinformation?totalAmount=${totalAmount}" class="btn btn-success">Mua hàng</a>
        </div>
        
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script>
        function increaseQuantity(itemId, maxQuantity) {
        	var quantityInput = document.getElementById("quantity_" + itemId);
            var oldQuantity = parseInt(quantityInput.value);
            var newQuantity = oldQuantity + 1;

            if (oldQuantity < maxQuantity) {
                quantityInput.value = newQuantity;
                updateCartItem(itemId, newQuantity);
            } else {
                alert("Số lượng đã đạt giới hạn.");
            }
            
        }

        function decreaseQuantity(itemId) {
            var quantityInput = document.getElementById("quantity_" + itemId);
            var oldQuantity = parseInt(quantityInput.value);
            var newQuantity = oldQuantity - 1;
            
            if (oldQuantity > 1) {
                quantityInput.value = newQuantity;
             	// Gọi hàm cập nhật số lượng
                updateCartItem(itemId, newQuantity); 
            }
        }
        
        function updateCartItem(itemId, quantity) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "${pageContext.request.contextPath}/updatecartitem", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Xử lý phản hồi từ server (nếu cần)
                    // Tải lại trang sau khi cập nhật giỏ hàng
                    location.reload(); 
                }
            };
            xhr.send("itemId=" + itemId + "&quantity=" + quantity);
        }
    </script>
</body>
<%@ include file="../footer.jsp"%>
</html>
