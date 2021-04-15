<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>Insert title here</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/themBV.css"%></style>
</head>
<body>
	<div class = "chung">
        <div class = "left">
            <br>
                <li><a href="admin/home.htm">Bài Viết</a></li>
                <hr>
                <li><a href="admin/themBV.htm">Thêm Bài viết</a></li>
                <hr>
                <li><a href="admin/qldm.htm">Quản lý danh mục</a></li>
                <hr>
                <li><a href="admin/qlTaiKhoan.htm">Quản lý tài khoản</a></li>
                <hr>
                <li><a href="admin/dsMail.htm">DS mail đã gửi</a></li>
                <hr>
                <li><a href="admin/tttk.htm">Thông tin tài khoản</a></li>
                <hr>
                <li><a href="user/home.htm">Trang chủ BLOG</a></li>
                <hr>
                <li><a href="user/login.htm">Đăng Xuất</a></li>
           
        </div>

        <div class = "right">
            <div class="form">
	            <form:form  action="admin/themBV.htm" method="POST" modelAttribute="baiViet" enctype="multipart/form-data">
	            ${message}
	            	<h1>THÊM BÀI VIẾT</h1>
	                <label for="">Tên tiêu đề</label>
	                <br>
	                <form:errors style="color: red;" class="err" path="tieuDe"/>
	                <input type="text" name="tieuDe" id="">
	               
	                <label for="">Ảnh</label>
	                <br>
	                <input type="file" name=hinhMH id="">
	                
	                <label for="">Nội dung</label>
	                <br>
	                <form:errors style="color: red;" class="err" path="noiDung"/>
	                <input type="text" name="noiDung" id="">
	               
	                <label for="">Danh mục</label> 
	                <form:select style="width: 480px;margin: 15px 0;border:  #05b993 solid 1px;padding: 16px;border-radius: 5px;" 
	                path="maDanhMuc.maDanhMuc" items="${danhMuc}" name="maDanhMuc" itemLabel="tenDanhMuc" itemValue="maDanhMuc"/>
	                <form:errors style="color: red;" class="err" path="maDanhMuc"/>
	              	<br>
	                <button>Thêm</button>
	            </form:form> 
            </div>
        </div>
    </div>
</body>
</html>