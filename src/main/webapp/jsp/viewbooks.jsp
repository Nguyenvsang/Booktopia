<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Những cuốn sách</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <style>
        .book-card {
            margin-bottom: 20px;
        }
    </style>
    <%@ include file="header.jsp"%>
</head>
<body>
    
    <div class="container">
        <h1>Mời bạn xem qua</h1>
        
        <form class="form-inline my-2 my-lg-0" method="GET" action="${pageContext.request.contextPath}/viewbooks">
        	<input class="form-control mr-sm-2" type="text" name="search" placeholder="Tìm kiếm từ khóa">
        	<button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Tìm kiếm</button>
    	</form>
    	
    	<c:if test="${param.search != null && !param.search.isEmpty()}">
	        <div>
	            <p>Tìm kiếm từ khóa: ${param.search}</p>
	        </div>
	    </c:if>

        <div class="dropdown mb-3 mt-3">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="categoryDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Lọc theo danh mục
            </button>
            <div class="dropdown-menu" aria-labelledby="categoryDropdown">
                <a class="dropdown-item" href="?">Tất cả</a>
                <c:forEach var="category" items="${categories}">
                    <a class="dropdown-item" href="?category=${category.id}">${category.name}</a>
                </c:forEach>
            </div>
        </div>

        <c:forEach var="book" items="${books}">
            <div class="card book-card">
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
                            <c:set var="categoryName" value=""/>
                            <c:forEach var="category" items="${categories}">
                                <c:if test="${category.id eq book.categoryId}">
                                    <c:set var="categoryName" value="${category.name}"/>
                                </c:if>
                            </c:forEach>
                            <p class="card-text">Danh mục: ${categoryName}</p>
                            <pre>${book.description}</pre>
                            <a href="${pageContext.request.contextPath}/detailbook?bookId=${book.id}" class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${totalPages > 1}">
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}">Trước</a>
                    </li>
                </c:if>

                <c:forEach var="pageNumber" begin="1" end="${totalPages}">
                    <li class="page-item ${pageNumber == currentPage ? 'active' : ''}">
                        <c:choose>
                            <c:when test="${pageNumber == currentPage}">
                                <span class="page-link">${pageNumber}</span>
                            </c:when>
                            <c:otherwise>
                                <a class="page-link" href="?page=${pageNumber}">${pageNumber}</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}">Sau</a>
                    </li>
                </c:if>
            </ul>
        </c:if>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
<%@ include file="footer.jsp"%>
</html>
