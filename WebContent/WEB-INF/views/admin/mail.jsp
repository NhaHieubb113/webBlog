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
<style><%@include file="/WEB-INF/resources/css/mail.css"%></style>
</head>
<body>
	 <div class = "chung">
	 	<form:form action="admin/mail.htm" modelAttribute="tka" enctype="multipart/form-data">
	 	${message}
	 		<h1>GỬI MAIL</h1>
            <label for="">Tên tiêu đề</label>
            <input type="text" name="name" id="">

            <label for="">Đến</label>
            <form:input path="taiKhoan" type="text" name="to" id=""/>

            <label for="">Nội dung</label>
            <textarea type="text" name="body" id="" cols="90" rows="10"></textarea>
                    
            <button>Gửi</button>
            
	 	</form:form>
    </div>
</body>
</html>