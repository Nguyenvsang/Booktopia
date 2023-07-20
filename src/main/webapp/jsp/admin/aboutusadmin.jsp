<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Về chúng tôi</title>
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
<%@ include file="headeradmin.jsp"%>
</head>
<body>
    <div class="container mt-4 mb-4">
        <h1>Về chúng tôi</h1>
        <p>Chào mừng đến với không gian cửa hàng văn thơ của chúng tôi. Truy cập mọi lúc mọi nơi với giao diện
        đơn giản. Bạn có thể tìm những điều thú vị qua từng tác phẩm hoặc nhớ về những kỉ niệm cũ mà những câu
        thơ những lời văn gợi ra</p>
        <p>Từng thể loại thơ là từng cách riêng để thể hiện cảm xúc qua câu chữ. Đôi lúc sẽ có những câu thơ khó
        hiểu, đôi lúc sẽ rất dễ hiểu, đôi lúc nhiều ý khác nhau ở trong một câu thơ ...</p>
        <p>Thế kỉ nào tôi mới biết đến thơ</p>
        <p>Ờ đó là thế kỉ hai mươi mốt</p>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/jsp/customer/loginaccount.jsp">Đổi vai trò</a>
    </div>
</body>
<%@ include file="footeradmin.jsp"%>
</html>
