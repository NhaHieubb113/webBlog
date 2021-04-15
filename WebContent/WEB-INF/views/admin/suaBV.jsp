<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>SuaBV</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/suaBV.css"%></style>
</head>
<body>
	<div class = "chung">
            <form:form  action="admin/suaBV.htm" modelAttribute="Suabv" enctype="multipart/form-data">
                 ${message}
	            	<h1>SỬA BÀI VIẾT</h1>
	                <label for="">Tên tiêu đề</label>
	                <%-- <br>
	                <form:errors style="color: red;" class="err" path="tieuDe"/> --%>
	                <form:input path="tieuDe" type="text" name="tieuDe" id=""/>
	         
	                <label for="">Ảnh</label>
	                <input type="file" name="hinh"/>
	                
	                <label for="">Nội dung</label>
	                <%-- <br>
	                <form:errors style="color: red;" class="err" path="noiDung"/> --%>
	                <form:input path="noiDung" type="text" name="noiDung" id=""/>
	                
	                <label for="">Danh mục</label> 
	                <%-- <form:errors style="color: red;" class="err" path="maDanhMuc"/>
	              	<br> --%>
	                <form:select style="width: 480px;margin: 15px 0;border:  #05b993 solid 1px;padding: 16px;border-radius: 5px;" path="maDanhMuc.maDanhMuc" items="${danhMuc}" name="maDanhMuc" itemLabel="tenDanhMuc" itemValue="maDanhMuc"/>
	               
	                <br>
	                <button>LƯU</button>
	        </form:form> 
    </div>
</body>
</html>