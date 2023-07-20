<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
        crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
    <style>
        .custom-bg-red {
            background-color: red;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-danger">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/admin/aboutusadmin.jsp">Booktopia Admin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <c:choose>
                    <c:when test="${not empty sessionScope.admin}">
                        <!-- Hiển thị "Username" và "Đăng xuất" và "Giỏ hàng" và "Đơn hàng" -->
                        <li class="nav-item active">
		                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/admin/indexadmin.jsp">Trang chủ <span
		                            class="sr-only">(current)</span></a>
		                </li>
		                <li class="nav-item">
		                    <a class="nav-link" href="${pageContext.request.contextPath}/managebook">Sản phẩm</a>
		                </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/managecategory">Danh mục</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/manageorder">Đơn hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/viewaccount">Chào
                                <c:out value="${admin.firstName}" />
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                                href="${pageContext.request.contextPath}/jsp/customer/logoutaccount.jsp">Đăng xuất</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</body>
</html>
