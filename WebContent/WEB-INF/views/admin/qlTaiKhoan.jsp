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
<style><%@include file="/WEB-INF/resources/css/qLyTaiKhoan.css"%></style>
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
                <h1>QUẢN LÝ TÀI KHOẢN</h1>
       
                <table>
                    <tr>
                        <th>Tên Người Dùng</th>
                        <th>Email</th>
                        <th>SĐT</th>
                        <th>Địa chỉ</th>
                        <th>Thao tác</th>
                        
                    </tr>
                    <c:forEach var="u" items="${tk}">
                    <tr>
                        <td>${u.tenND}</td>
                    	<td>${u.taiKhoan}</td>
                        <td>${u.sdt}</td>
                    	<td>${u.diaChi}</td>
                        <td><a href="admin/delete1.htm?id=${u.taiKhoan}">Xóa</a><a href="admin/mail.htm?idSendMail=${u.taiKhoan}">GuiMail</a></td>
                        
                    </tr>
                    </c:forEach>
                    <c:if test="${message1 == true}">
		            	<script type="text/javascript">
							alert("Xóa thành công!");
						</script>
	            	</c:if>
		            <c:if test="${message1 == false}">
		            	<script type="text/javascript">
							alert("Xóa khong thành công!");
						</script>
		            </c:if>
                </table>
            </div>
        </div>
    </div>
</body>
</html>