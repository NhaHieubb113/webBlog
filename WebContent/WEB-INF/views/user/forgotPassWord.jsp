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
<style><%@include file="/WEB-INF/resources/css/Forgot_Password.css"%></style>
</head>
<body>
	<div class = "Center">
		<form:form action="user/forgotPassword.htm" modelAttribute="u">
			<h1>ĐẶT LẠI MẬT KHẨU</h1>
            <p>Nhập tài khoản của bạn:</p>
            <input type="text" placeholder="Email" name = "taiKhoan">
            <form:errors style="color: red;" class="err" path="taiKhoan"/>
            <br>
            <button>OK</button>
            <c:if test="${message == true}">
				<h3>Đổi mật khẩu thành công! Vui lòng kiểm tra email để xác nhận mật khẩu mới. Xin cảm ơn <a href="user/login.htm">Login tại đây!</a></h3>
			</c:if> 
		</form:form>
        
    </div>
</body>
</html>