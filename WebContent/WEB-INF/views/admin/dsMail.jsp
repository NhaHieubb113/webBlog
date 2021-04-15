<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>DanhSachMai</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/dsMail.css"%></style>
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
            <table>
                <h1>DANH SÁCH MAIL ĐÃ GỬI</h1>
                   <tr>
                      <th>MaBinhLuan</th>
                      <th>Người nhận</th>
                      <th>Tiêu Đề</th>
                      <th>Nội Dung</th>
                      <th>Thời Gian</th>
                   </tr>
                   <c:forEach var="u" items="${mail}">
                      <tr>
                          <td>${u.maMail}</td>
                          <td>${u.nguoiNhan.taiKhoan}</td>
                          <td>${u.tuaDe}</td>
                          <td>${u.noiDung}</td>
                          <td>${u.thoiGian}</td>
                      </tr>
                  </c:forEach>
           </table>
        </div>
    </div>
</body>
</html>