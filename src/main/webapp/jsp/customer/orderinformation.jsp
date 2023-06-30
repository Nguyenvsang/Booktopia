<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin thanh toán</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <%@ include file="../header.jsp"%>
</head>
<body>
    <div class="container-fluid mb-4">
        <h1 class="text-center">Thông tin thanh toán</h1>

        <form action="${pageContext.request.contextPath}/placeorder" method="post">
            <div class="form-group row">
			    <label for="name" class="col-md-2 col-form-label">Tên:</label>
			    <div class="col-md-10">
			        <input type="text" id="name" name="name" value="${account.firstName} ${account.lastName}" required class="form-control">
			    </div>
			</div>
            <div class="form-group row">
                <label for="address" class="col-md-2 col-form-label">Địa chỉ:</label>
                <div class="col-md-10">
                    <input type="text" id="address" name="address" value="${account.address}" required class="form-control">
                </div>
            </div>
            <div class="form-group row">
                <label for="phoneNumber" class="col-md-2 col-form-label">Số điện thoại:</label>
                <div class="col-md-10">
                    <input type="text" id="phoneNumber" name="phoneNumber" value="${account.phoneNumber}" required class="form-control">
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-md-2 col-form-label">Email:</label>
                <div class="col-md-10">
                    <input type="email" id="email" name="email" value="${account.email}" required class="form-control">
                </div>
            </div>
            <div class="form-group row">
                <label for="paymentMethods" class="col-md-2 col-form-label">Phương thức thanh toán:</label>
                <div class="col-md-10">
                    <input type="text" id="paymentMethods" name="paymentMethods" value="Hiện chỉ có thanh toán khi nhận hàng" readonly class="form-control">
                </div>
            </div>
            <div class="text-center alert alert-info">
	        	<h4 >Tổng số tiền: ${totalAmount} vnd</h4>
	        </div>
            <div class="text-center">
			    <button type="submit" class="btn btn-primary">Đặt hàng</button>
			    <a href="javascript:history.back()" class="btn btn-secondary">Hủy</a>
			</div>
        </form>
    </div>

    <%@ include file="../footer.jsp"%>
</body>
</html>
