<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booktopia - Văn thơ Việt Nam</title>
<%@ include file="headeradmin.jsp"%>
</head>
<body>
<div id="carouselExampleControls" class="carousel slide d-flex" data-ride="carousel" data-interval="3000" data-pause="false">
  <div class="carousel-inner">
    <div class="carousel-item active justify-content-center align-items-center">
      <img src="https://res.cloudinary.com/dosdzo1lg/image/upload/v1685797301/Booktopia/img_book/book_1.jpg" class="d-block mx-auto" alt="...">
    </div>
    <div class="carousel-item justify-content-center align-items-center">
      <img src="https://res.cloudinary.com/dosdzo1lg/image/upload/v1686319023/Booktopia/img_book/book_2.jpg" class="d-block mx-auto" alt="...">
    </div>
    <div class="carousel-item justify-content-center align-items-center">
      <img src="https://res.cloudinary.com/dosdzo1lg/image/upload/v1686319023/Booktopia/img_book/book_3.jpg" class="d-block mx-auto" alt="...">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
</body>
<%@ include file="footeradmin.jsp"%>
</html>