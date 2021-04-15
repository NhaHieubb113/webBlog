<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>CreateAC</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/createAC.css"%></style>
</head>
<body>
	<div class="khungdangnhap">
        <div class="phai">
            <div class="picture">
                <img src="imgs/nguoinghenhca.jpg" alt="">
            </div>
        </div>
        <div class="trai">
        	<form:form action="" modelAttribute="User" >
        		${message}
        		${message1}
                <h1>ĐĂNG KÝ</h1>
                <!-- <label for="">name</label> -->
                <p>Tên Tài khoản</p>
                <input type="text" name="tenND" placeholder="Tên người dùng">
                <form:errors style="color: red;" class="err" path="tenND"/>
                
                <p>Tài khoản</p>
                <input type="text" name="taiKhoan" placeholder="Email">
                <form:errors style="color: red;"  class="err" path="taiKhoan"/>
                
                <p>Địa chỉ</p>
                <input type="text" name="diaChi" placeholder="Địa chỉ">
                <form:errors style="color: red;" class="err" path="diaChi"/>
                
                <p>Số điện thoại</p>
                <input type="text" name="sdt" placeholder="SĐT">
                <form:errors style="color: red;" class="err" path="sdt"/>
                
                <p>Mật khẩu</p>
                <input type="password" name="matKhau" id="" placeholder="Password">
                <form:errors style="color: red;" class="err" path="matKhau"/>
                
                 <p>Xác nhận lại mật khẩu</p>
        		<input type="password" name="pass-conf" placeholder="password...">
        		<form:errors style="color: red;" class="err" path="matKhau"/>
                <br>
                <button>Đăng ký</button>
        	</form:form>
          </div>

    </div>
</body>
</html>