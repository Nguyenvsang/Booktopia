<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin sách</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<%@ include file="header.jsp"%>
</head>
<body>
    <div class="container-fluid mb-4">
        <h1>Thông tin sách</h1>
        
        <%-- Decode the "message" parameter --%>
	    <%
	       String messageId = request.getParameter("message");
	       String message = "";
	       String messageType = "";
	       if (messageId != null) {
	           message = java.net.URLDecoder.decode(messageId, "UTF-8");
	           messageType = (message.toLowerCase().contains("thành công")) ? "alert-success" : "alert-danger";
	       }
	       pageContext.setAttribute("message", message);
	       pageContext.setAttribute("messageType", messageType);
	    %>
        
        <div class="card">
            <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="${book.img}" alt="Book Image" class="card-img">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${book.name}</h5>
                        <p class="card-text">Tác giả: ${book.author}</p>
                        <p class="card-text">Giá: ${book.price} vnd</p>
                        <p class="card-text">Số lượng: ${book.quantity}</p>
                        <!-- Hiển thị thông báo lỗi, thành công khi thêm vào giỏ hàng -->
				        <c:if test="${not empty message}">
				            <div class="alert ${messageType}" role="alert">
				                ${message}
				            </div>
				        </c:if>
                        <div class="d-flex flex-grow-1">
					        <form action="${pageContext.request.contextPath}/addtocart" method="post">
						        <div class="row">
						            <div class="col-4">
						                <input type="number" id="quantity" name="quantity" class="form-control" min="1" max="${book.quantity}" value="1">
						            </div>
						            <div class="col-8">
						                <input type="hidden" name="bookId" value="${book.id}">
						                <button type="submit" class="btn btn-success btn-block">Thêm vào giỏ hàng</button>
						            </div>
						        </div>
						    </form>
					        <div class="ml-2">
					        	<button onclick="goBack()" class="btn btn-primary">Trở về</button>
					        </div>
					    </div>
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
<%@ include file="footer.jsp"%>
</html>