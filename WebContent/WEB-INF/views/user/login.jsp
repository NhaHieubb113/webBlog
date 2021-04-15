<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>Login</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/login.css"%></style>
</head>
<body>
	<div class="khungdangnhap">
        <div class="phai">
            <div class="picture">
                <img src="imgs/nguoinghenhca.jpg" alt="">
                <br>
                <p>Chưa có tài khoản? <a href="user/createAC.htm">Đăng ký tại đây!</a></p>
            </div>
        </div>
        <div class="trai">
        	<form:form action="" modelAttribute="User">
        	 ${message}
        	 ${message1}
        		<h1>ĐĂNG NHẬP</h1>
               
                <p>Tài khoản</p>
                <form:errors style="color: red;" class="err" path="taiKhoan"/>
                <input type="text" name="taiKhoan" placeholder="Email">
                
                <p>Password</p>
                <form:errors style="color: red;" class="err" path="matKhau"/>
                <input type="password" name="matKhau" id="" placeholder="Password">
               
                <br>
                <!-- <br> xuong hang -->
                <a href="user/forgotPassword.htm?se=true">Quên mật khẩu</a>
                <br>
                <button>Đăng nhập</button>
               
        	</form:form>

        </div>

    </div>
</body>
</html>