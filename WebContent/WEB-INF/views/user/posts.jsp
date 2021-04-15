<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>SHOW_1BV</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/posts.css"%></style>
</head>
<body>
	<div class="top">
        <div class="left">
            <a href="user/home.htm">BLOG MANGO</a>

        </div>
        <div class="center">
            <c:forEach var ="u" items="${dm}">
	          <a href="user/${u.maDanhMuc}.htm?code=${u.maDanhMuc}" style="text-transform: uppercase;">${u.tenDanhMuc}</a>
           </c:forEach>

        </div>
        <div class="right">
            <a href="/login.html">ĐĂNG XUẤT</a>
        </div>
    </div>
    <div class="center">
        <input type="text"placeholder="Tìm kiếm của bạn">
        <button>SEARCH</button>
    </div>
    <div class="bot">
    	<%-- <form:form action="" modelAttribute="post"> --%>
    		<div class="left" action="" modelAttribute="them" >
            <!-- <img src="imgs/h6.jpg" alt=""> -->
            <td><img src="imgs/${them.hinhMH}" alt=""></td>
            
        	</div>

	        <div class="right">
	            <h1>${them.tieuDe }</h1>
	            <p> 
	                ${them.noiDung} 
	            </p>
           
            </div>
    	<%-- </form:form> --%>
        
    </div>
    <div class="binhLuan">
        <div class="form" >
        
        	<form:form action="" modelAttribute="BL">
        		${message1}
        		${message}
        		<h1>BÌNH LUẬN</h1>
	            <input type="text" name="noiDung" placeholder="Bình luận" id="">
	            <button>Đăng</button>
        	</form:form>
            
            <form:form >
            	 <!-- Vòng lặp -->
	            <c:forEach var = "p" items="${binhLuan}">
	            	<%-- <c:if test="${p.maBaiViet.maBaiViet==them.maBaiViet}" > --%>
	            		<h3>${p.taiKhoan.taiKhoan}</h3>
	            		<p>${p.noiDung}</p>
            		<%-- </c:if> --%>
            	</c:forEach>
            </form:form>
           
        </div>
    </div>
</body>
</html>