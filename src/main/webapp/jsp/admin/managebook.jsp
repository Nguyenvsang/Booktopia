<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý sách</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <style>
        th, td {
            text-align: center;
        }
    </style>
    <%@ include file="headeradmin.jsp"%>
</head>
<body>
    
    <div class="container-fluid mb-4">
        <h1>Quản lý sách</h1>
        
	    <div class="mb-3">
	        <a href="${pageContext.request.contextPath}/addbook" class="btn btn-primary">Thêm sách</a>
	    </div>
        
        <form class="form-inline my-2 my-lg-0" method="GET" action="${pageContext.request.contextPath}/managebook">
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

        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                	<th scope="col" class="align-middle text-center">Mã</th> 
                    <th scope="col" class="align-middle text-center">Ảnh</th>
                    <th scope="col" class="align-middle text-center">Tên sách</th>
                    <th scope="col" class="align-middle text-center">Tác giả</th>
                    <th scope="col" class="align-middle text-center">Giá</th>
                    <th scope="col" class="align-middle text-center">Số lượng</th>
                    <th scope="col" class="align-middle text-center">Danh mục</th>
                    <th scope="col" class="align-middle text-center">Mô tả</th>
                    <th scope="col" class="align-middle text-center">Nội dung</th>
                    <th scope="col" class="align-middle text-center">Trạng thái</th>
                    <th scope="col" class="align-middle text-center">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                    	<td class="align-middle text-center">${book.id}</td>
                        <td class="align-middle text-center"><img src="${book.img}" alt="Book Image" style="max-height: 100px;"></td>
                        <td class="align-middle text-center">${book.name}</td>
                        <td class="align-middle text-center">${book.author}</td>
                        <td class="align-middle text-center">${book.price} vnd</td>
                        <td class="align-middle text-center">${book.quantity}</td>
                        <c:set var="categoryName" value=""/>
                        <c:forEach var="category" items="${categories}">
                            <c:if test="${category.id eq book.categoryId}">
                                <c:set var="categoryName" value="${category.name}"/>
                            </c:if>
                        </c:forEach>
                        <td class="align-middle text-center">${categoryName}</td>
                        <td class="align-middle text-center">${book.description}</td>
                        <td class="align-middle text-center">${book.detail}</td>
                        <td class="align-middle text-center">
                            <c:choose>
                                <c:when test="${book.status eq 1}">
                                	<div class="alert alert-success" role="alert">Đang kinh doanh</div>
								</c:when>
                                <c:when test="${book.status eq 0}">
                                	<div class="alert alert-danger" role="alert">Không kinh doanh</div>
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="align-middle text-center">
                            <a href="${pageContext.request.contextPath}/managedetailbook?bookId=${book.id}" class="btn btn-primary">Xem chi tiết</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

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
<%@ include file="footeradmin.jsp"%>
</html>
