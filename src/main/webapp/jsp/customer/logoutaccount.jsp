<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng xuất</title>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="container d-flex align-items-center justify-content-center vh-100">
    <div class="card text-center">
        <div class="card-body">
            <h3 class="card-title">Bạn có muốn đăng xuất?</h3>
            <form action="${pageContext.request.contextPath}/logoutaccount" method="post">
                <button type="submit" class="btn btn-danger">Đăng xuất</button>
                <a href="${pageContext.request.contextPath}/jsp/index.jsp" class="btn btn-secondary">Không</a>
            </form>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp"%>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
