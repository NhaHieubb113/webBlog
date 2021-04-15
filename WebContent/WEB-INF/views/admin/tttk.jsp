<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>Insert title here</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/TTTKhoan.css"%></style>
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
            <form:form action="" modelAttribute="SuaTTTK">
            ${message1}
            	<h1>THÔNG TIN TÀI KHOẢN</h1>
                <label for="">Tài khoản</label>
                <form:input path="taiKhoan" type="text" name="taiKhoan" id="" readonly="true" />
                
                <label for="">Tên người dùng</label>
                <form:input path="tenND" type="text" name="tenND" id=""/>
               	
                <label for="">SĐT</label>
                <form:input path="sdt" type="text" name="sdt" id=""/>
                
                <label for="">Địa Chỉ</label>
                <form:input path="diaChi" type="text" name="diaChi" id=""/>
                
                <label for="">Mật Khẩu</label>
                <form:input path="matKhau" type="password" name="" id="" placeholder="Password"/>
                <br>
                <button>LƯU</button>

                
          </form:form>
                
        </div>
   </div>
</body>
</html>