<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>Admin Home</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/admin_home.css"%></style>

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
                <h1>BÀI VIẾT</h1>
                <tr>
                    <th>Ảnh</th>
                    <th>Mã bài viết</th>
                    <th>Danh Mục</th>
                    <th>Tiêu Đề</th>
                    <th>Thời Gian</th>
                    <th>Thao tác</th>
                </tr>
                <c:forEach var="u" items="${bv}">
                ${message1}
                <tr>
                    <%-- <td><a href="admim/qlBinhLuan.htm"><img src="${u.hinhMH}" alt=""></a></td> --%>
                    <td><a href="admin/qlBinhLuan.htm?id=${u.maBaiViet}"><img style="width: 100px;" src="imgs/${u.hinhMH}" alt=""></td>
                    <%-- <td>${u.HinhMinhHoa}</td> <img src="imgs/h1.jpg" alt=""> --%>
                    <td>${u.maBaiViet}</td>
                    <td>${u.maDanhMuc.tenDanhMuc}</td>
                    <td>${u.tieuDe}</td>
                    <td>${u.thoiGian}</td>
                    <td>
                        <div class="but">
                            <a href="admin/delete_bv.htm?id=${u.maBaiViet}">Xóa</a><a href="admin/suaBV.htm?idUpdate=${u.maBaiViet}">Sửa</a>
                        </div>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>