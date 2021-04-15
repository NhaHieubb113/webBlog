<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta  charset="utf-8"/>
<title>Insert title here</title>
<base href = "${pageContext.servletContext.contextPath}/">
<style><%@include file="/WEB-INF/resources/css/home.css"%></style>
</head>
<body>
	<div class="body">
        <div class="chungTren">
            <div class="top">
                <div class="gon">
                    <div class="left">
                        <a href="user/home.htm">BLOG MANGO</a>
    
                    </div>
                    <div class="center">
                        <c:forEach var ="u" items="${dm}">
	                        <a href="user/${u.maDanhMuc}.htm?code=${u.maDanhMuc}" style="text-transform: uppercase;">${u.tenDanhMuc}</a>
                        </c:forEach>
                        
    
                    </div>
                    <div class="right">
                        <c:if test="${sessionScope.taiKhoan == null}">
	                    		<a href="user/login.htm?se=true">CHƯA ĐĂNG NHẬP</a>
                        </c:if>
                        <c:if test="${sessionScope.taiKhoan != null}">
	                    		<a href="user/tttk.htm">TÀI KHOẢN</a>
	                    		<a href="user/login.htm">ĐĂNG XUẤT</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="bot">
                <div class="gom">
                    <h1>HÃY ĐẾN VỚI BLOG DU LỊCH</h1>
                    <p>Khám phá và chia sẻ những địa điểm du lịch nổi tiếng trên khắp Việt Nam</p>
                 
                </div>
            </div>
        </div>
        <div class="chungDuoi">
            <div class="thanhTK">
                <input type="text"placeholder="Tìm kiếm của bạn">
                <button>SEARCH</button>
            </div>
            <div class="hinhTravel">
                <div class="trai">
                    <div class="tren">
                        <h1>NHỮNG TIN TỨC NỔI BẬT NHẤT </h1>
                    </div>
    
                    <div class="flex">
	                    <c:forEach var ="u" items="${show_ds}" >
	                    	<div class="gomnua">
	                            <div class="duoi">
	                                <a href="user/posts.htm?id=${u.maBaiViet}">
	                                    <img src="imgs/${u.hinhMH}" alt="">
	                            </div>
	                            <div class="tieuDe">
	                                <a href="user/posts.htm?id=${u.maBaiViet}">${u.tieuDe}</a>
	                            </div>
	                        </div>
	                    </c:forEach>
                        
               		</div>
                </div>
                <div class ="phai">
                    <div class="phai_top chung">
                        <img src="imgs/CO1.jpg" alt="">
                    </div>

                    <div class="phai_center chung">
                        <h2>TIN MỚI</h2>
                        <div class="gomnua">
                            <div class="duoi">
                                <a href="user/posts.htm?id=5">
                                    <img src="imgs/h8.jpg" alt="">
                            </div>
                            <div class="tieuDe">
                                <a href="user/posts.htm?id=5">Chợ Nổi miền tây</a>
                            </div>
                        </div>
                    </div>

                    <div class="phai_bot chung">
                        <h2>BLOG DU LỊCH</h2>
                        <p>Nơi chia sẻ những câu chuyện, cảm nhận về những chuyến du lịch, nền ẩm thực độc đáo của đất nước Việt Nam</p>
                        <p></p>
                        <img src="imgs/VN.jpg" alt="">
                        
                    </div>
                </div>

            </div>     
        </div>
        <div class="cuoiCung">
            <h1>BLOG MANGO</h1>
            <P>Liên hệ: 0822718408</P>
            <p>Địa chỉ: 97 đường Man Thiện, phường Hiệp Phú, quận 9</p>
            <p>Email: n17dccn048@student.ptithcm.edu.vn</p>

        </div>
    </div>
</body>
</html>