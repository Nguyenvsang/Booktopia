<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý đơn hàng</title>
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
        <h1>Quản lý đơn hàng</h1>
        
        <form class="form-inline my-2 my-lg-0" method="GET" action="${pageContext.request.contextPath}/manageorder">
            <input class="form-control mr-sm-2" type="text" name="search" placeholder="Tìm kiếm từ khóa">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Tìm kiếm</button>
        </form>
        
        <c:if test="${param.search != null && !param.search.isEmpty()}">
            <div>
                <p>Tìm kiếm từ khóa: ${param.search}</p>
            </div>
        </c:if>
        
        <div class="my-2 my-lg-0 mt-3 mb-3">
		    <label for="status">Trạng thái:</label>
		    <form class="form-inline" method="GET" action="${pageContext.request.contextPath}/manageorder">
		        <select class="form-control" id="status" name="status" onchange="this.form.submit()">
		            <option value="-1" <c:if test="${param.status == '-1'}">selected</c:if>>Tất cả</option>
		            <option value="0" <c:if test="${param.status == '0'}">selected</c:if>>Chờ xác nhận</option>
		            <option value="1" <c:if test="${param.status == '1'}">selected</c:if>>Chờ lấy hàng</option>
		            <option value="2" <c:if test="${param.status == '2'}">selected</c:if>>Đang giao</option>
		            <option value="3" <c:if test="${param.status == '3'}">selected</c:if>>Đã giao</option>
		        </select>
		    </form>
		</div>

        <table class="table table-striped table-bordered mt-3">
            <thead class="thead-dark">
                <tr>
                    <th scope="col" class="align-middle text-center">Mã đơn hàng</th>
                    <th scope="col" class="align-middle text-center">Ngày đặt</th>
                    <th scope="col" class="align-middle text-center">Tổng tiền</th>
                    <th scope="col" class="align-middle text-center">Trạng thái</th>
                    <th scope="col" class="align-middle text-center">Tên</th>
                    <th scope="col" class="align-middle text-center">Địa chỉ</th>
                    <th scope="col" class="align-middle text-center">Số điện thoại</th>
                    <th scope="col" class="align-middle text-center">Email</th>
                    <th scope="col" class="align-middle text-center">Chi tiết</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td class="align-middle text-center">${order.id}</td>
                        <td class="align-middle text-center">${order.dateOrder}</td>
                        <td class="align-middle text-center">${order.totalPrice}</td>
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
                        <td class="align-middle text-center">${order.name}</td>
                        <td class="align-middle text-center">${order.address}</td>
                        <td class="align-middle text-center">${order.phoneNumber}</td>
                        <td class="align-middle text-center">${order.email}</td>
                        <td class="align-middle text-center">
                            <a href="${pageContext.request.contextPath}/manageorderitems?orderId=${order.id}" class="btn btn-primary">Xem chi tiết</a>
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