<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>QLDM</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/admin_QLDM.css"%></style>
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
            <div class="chung-right">
                <form:form action="" modelAttribute="dme" >
                ${message}
        		${message1}
                    <h1>QUẢN LÝ DANH MỤC</h1>
                    <label for="">Tên Danh Mục</label>
                    <form:input path="tenDanhMuc" id=""/>
                    <form:errors class="err" path="tenDanhMuc"/>
                    <label for="">Mã Danh Mục</label>
                    <form:input path="maDanhMuc" id=""/>
                    <form:errors class="err" path="maDanhMuc"/>
                    <button>Thêm</button>
                </form:form>
                <table>
                    <tr>
                        <th>Tên Danh Mục</th>
                        <th>Mã Danh mục</th>
                        <th>Thao tác</th>
                    </tr>
                    <c:forEach var="u" items="${dm}"> 
                    <tr>
                        <td>${u.tenDanhMuc}</td>
                    	<td>${u.maDanhMuc}</td>
                        <td><a href="admin/delete.htm?id=${u.maDanhMuc}">Xóa</a><a href="admin/update.htm?idUpdate=${u.maDanhMuc}" id="sua">Sửa</a></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</body>
</html>