<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng nhập</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<%@ include file="../header.jsp"%>
</head>
<body>
<div class="container mt-4 mb-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h3 class="text-center">Đăng nhập</h3>
            <% 
                // Kiểm tra xem có thông báo lỗi không
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
                <div class="alert alert-danger" role="alert">
                    <%= message %>
                </div>
            <% } %>
            <form action="${pageContext.request.contextPath}/loginaccount" method="post">
                <div class="form-group">
                    <label for="username">Tên đăng nhập:</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<%@ include file="../footer.jsp"%>
</html>
