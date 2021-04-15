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
<style><%@include file="/WEB-INF/resources/css/qLyBinhLuan.css"%></style>
</head>
<body>
	<div class = "chung">
        <table>
            <h1>QUẢN LÝ BÌNH LUẬN</h1>
            ${message1}
            <tr>
            	<th>Mã bình luận</th>
                <th>Mã bài viết</th>
                <th>Tiêu đề bài viết</th>
                <th>Tài khoản</th>
                <th>Nội Dung bình luận</th>
                <th>Thời Gian</th>
                <th>Thao tác</th>
            </tr>
            <c:forEach var="u" items="${bl}">
            	 <tr>
            	<td>${u.maBinhLuan}</td>
                <td>${u.maBaiViet.maBaiViet}</td>
                <td>${u.maBaiViet.tieuDe}</td>
                <td>${u.taiKhoan.taiKhoan}</td>
                <td>${u.noiDung}</td>
                <td>${u.thoiGian}</td>
                <td><a href="admin/delete2.htm?id=${u.maBinhLuan}">Xóa</a></td>
            </tr>
           
            </c:forEach>
        </table>
    </div>
</body>
</html>