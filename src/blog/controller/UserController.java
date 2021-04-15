package blog.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import blog.entity.*;
@Controller
@Transactional
@RequestMapping("user")

public class UserController {

	@Autowired
	JavaMailSender mailer;
	
	@Autowired
	SessionFactory factory;
	
//HOME
	@RequestMapping("home")
	public String home() {
		
		return "user/home";
	}
	
//PASS
public static String taiKhoan="";
@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
public String forget() {
	return "user/forgotPassWord";
}

@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
public String forget(ModelMap model, @ModelAttribute("u") UserEntity u) {

	Session session = factory.openSession();
	Transaction t = session.beginTransaction();
	String hql = "FROM UserEntity";
	Query query = session.createQuery(hql);
	List<UserEntity> listAcc = query.list();
	for(UserEntity user: listAcc) {
		if(user.getTaiKhoan().equals(u.getTaiKhoan())) {
			int code = (int) Math.floor(((Math.random() * 8999999) + 1000000));
			String kq=String.valueOf(code);
			try {
				user.setMatKhau(kq);
				session.update(user);
				t.commit();
				sendMail(user.getTaiKhoan(), user.getMatKhau());
				model.addAttribute("message", true);
			}
			catch(Exception e) {
				t.rollback();
				model.addAttribute("message", "That bai");
			}
			finally {
				session.close();
			}
		}
	}
	return "user/forgotPassWord";
}


//LOGIN
@RequestMapping(value = "/login", method = RequestMethod.GET)
public String login(HttpServletRequest request, ModelMap model) throws ServletException, IOException {

	taiKhoan ="";
	model.addAttribute("User", new UserEntity());
	return "user/login";
}

@RequestMapping(value = "/login", method = RequestMethod.POST)
public String login(@ModelAttribute("User") UserEntity user, HttpServletRequest request, ModelMap model, BindingResult errors)
		throws ServletException, IOException {
	Session session = factory.getCurrentSession();
	String hql = "FROM UserEntity";
	
	Query query = session.createQuery(hql);
	List<UserEntity> listAcc = query.list();
	taiKhoan ="";
	
	if(user.getTaiKhoan().trim().length() == 0) {
		errors.rejectValue("taiKhoan", "User", "Vui lòng nhập email!");
	}
	if(user.getMatKhau().trim().length() == 0) {
		errors.rejectValue("matKhau", "User", "Vui lòng nhập mật khẩu!");
	}
	if(errors.hasErrors()) {
		model.addAttribute("message1", "Vui lòng điền thông tin đầy đủ!");
	}
	else {
		boolean check = true;
		for (UserEntity ktra : listAcc) {
			if (user.getTaiKhoan().equals(ktra.getTaiKhoan()) && user.getMatKhau().equals(ktra.getMatKhau()) && ktra.getBitKhoa() == 1){
				
				check = false;
				taiKhoan = ktra.getTaiKhoan();
				HttpSession s = request.getSession();
		        s.setAttribute("taiKhoan", ktra.getTaiKhoan());
				if(ktra.getBitQuyen()== 1) {
					return "redirect:/admin/home.htm";
				}
			}
		}
		if(check == false) {
			return "redirect:/user/home.htm";
			
	
		}
		else {
			model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác.");
		}
	}

	return "user/login";
}



//DANG KI	
@RequestMapping(value = "/createAC", method = RequestMethod.GET)
public String register(ModelMap model) throws ServletException, IOException {
	model.addAttribute("User", new UserEntity());
	return "user/createAC";
}

@RequestMapping(value = "createAC", method = RequestMethod.POST)
public String register(@ModelAttribute("User") UserEntity user, BindingResult errors, HttpServletRequest request, ModelMap model)
		throws ServletException, IOException {
	String pass_conf= request.getParameter("pass-conf");
	Session s = factory.openSession();
	Transaction t = s.beginTransaction();
	boolean check = true;
	String hql = "FROM UserEntity";
	Query query = s.createQuery(hql);
	List<UserEntity> listAcc = query.list();
	if(user.getTenND().trim().length() == 0) {
		errors.rejectValue("tenND", "User", "Vui lòng nhập tênND!");
	}		
	if(user.getTaiKhoan().trim().length() == 0) {
		errors.rejectValue("taiKhoan", "User", "Vui lòng nhập email!");
	}
	if(user.getDiaChi().trim().length() == 0) {
		errors.rejectValue("diaChi", "User", "Vui lòng nhập địa chỉ!");
	}
	if(user.getSdt().trim().length() == 0) {
		errors.rejectValue("sdt", "User", "Vui lòng nhập số điện thoại!");
	}
	if(user.getMatKhau().trim().length() == 0) {
		errors.rejectValue("matKhau", "User", "Vui lòng nhập mật khẩu!");
	}
	if(user.getMatKhau().equals(pass_conf)) {
		
	}
	else {
		errors.rejectValue("matKhau", "User", "Mật khẩu không giống nhau!");
	}
	if(errors.hasErrors()) {
		model.addAttribute("message1", "Vui lòng điền thông tin đầy đủ!");
	}
	else {
		for (UserEntity ktra : listAcc) {
			if (user.getTaiKhoan().equals(ktra.getTaiKhoan())) {
				check = false;
				errors.rejectValue("taiKhoan", "User", "tài khoản đã tồn tại!");
			}
		}
		if(check == true) {
			user.setBitQuyen(0);
			user.setBitKhoa(1);
			s.save(user); //luu do db
			t.commit(); //chap nhan thay doi du lieu trong db
			model.addAttribute("message", "Thành công");
			return "redirect:/user/login.htm";
		}
		else {
			model.addAttribute("message", "Thất bại"
					+ "");
		}
	}
	return "user/createAC";
}

	
//SHOW DS BAI VIẾT
	@RequestMapping(value="show_ds")
	public String show_ds(ModelMap model) {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		boolean check = true;
		String hql = "FROM BaiVietEntity";
		Query query = s.createQuery(hql);
		List<BaiVietEntity> listPro = query.list();
		model.addAttribute("list",listPro);
		
		return "user/home";
	}
	

	@ModelAttribute("show_ds")
	public List<BaiVietEntity> baiViet(ModelMap model) {
		Session s = factory.getCurrentSession();
		String hql = "FROM BaiVietEntity";
		Query query = s.createQuery(hql);
		List<BaiVietEntity> listbv = query.list();
		
		return listbv;
	}	
	
//POST BAI VIET	
			int code = 0;
			@RequestMapping(value="/posts", method = RequestMethod.GET)
			public String posts(ModelMap model, HttpServletRequest request) {
				code = Integer.parseInt(request.getParameter("id"));
				model.addAttribute("binhLuan", bl(code));
				model.addAttribute("them", selectMaBV(code));
				
				return "user/posts";
			}
			
			//láy bình luạn của 1 bai viet
//			@ModelAttribute("binhLuan")
			public List<BinhLuanEntity> bl(int code) {
				Session s = factory.getCurrentSession();
				String hql = "FROM BinhLuanEntity where maBaiViet = "+code;
				Query query = s.createQuery(hql);
				List<BinhLuanEntity> listBL = query.list();
				System.out.print(code);
				return listBL;
			}

			//SELECT MA BAI VIET
		
			public BaiVietEntity selectMaBV(int code) {
				BaiVietEntity bviet =null;
				Session session = factory.getCurrentSession();
				String hql = "from BaiVietEntity";
				Query query = session.createQuery(hql);
				List<BaiVietEntity> list = query.list();
				for(BaiVietEntity u: list) {
					if(u.getMaBaiViet() == code) {
						bviet = u;
					}
				}
				return bviet;

			}
			
			
//THÊM BÌNH LUẬN CHO 1 BAI VIẾT
		
			@RequestMapping(value = "posts", method = RequestMethod.POST)
			public String themBL(@ModelAttribute("BL") BinhLuanEntity bl, BindingResult errors, HttpServletRequest request, ModelMap model)
					throws ServletException, IOException {
				Session session = factory.openSession();
				Transaction t = session.beginTransaction();
				
				code = Integer.parseInt(request.getParameter("id"));
				model.addAttribute("binhLuan", bl(code));
				model.addAttribute("them", selectMaBV(code));
				
				try {
					bl.setThoiGian(new Date());
					bl.setTaiKhoan(selectTK(taiKhoan));
					bl.setMaBaiViet(selectMaBV(code));
					session.save(bl);
					t.commit();
					model.addAttribute("message", "Đã lưu");
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Lưu không thành công!");
					System.out.print(e.getMessage());
				} finally {
					session.close();
				}
				
				return "redirect:/user/posts.htm?id="+code;
			}

			
//SHOW DS THEO DANH MUC
			@RequestMapping("/{maDanhMuc}")
			public String code(ModelMap model, HttpServletRequest  request, @PathVariable("maDanhMuc") String mdm){
				Session session = factory.getCurrentSession();
				String hql1 = "FROM BaiVietEntity p WHERE p.maDanhMuc.maDanhMuc LIKE :danhMuc";
				Query query1 = session.createQuery(hql1);
				query1.setParameter("danhMuc", mdm);
				List<BaiVietEntity> list1 = query1.list();
				model.addAttribute("danhMuc", list1);

				return "user/show_type";
			}		
			
			
			//lay danh sach danh muc
			@ModelAttribute("dm")
			public List<DanhMucEntity> mdm() {
				Session s = factory.getCurrentSession();
				String hql = "FROM DanhMucEntity ";
				Query query = s.createQuery(hql);
				List<DanhMucEntity> listBL = query.list();
				return listBL;
			}


//SELECT TAI KHOAN
		public UserEntity selectTK(String code) {
			
			UserEntity ma =null;
			Session session = factory.getCurrentSession();
			String hql = "from UserEntity";
			Query query = session.createQuery(hql);
			List<UserEntity> list = query.list();
			for(UserEntity u: list) {
				if(u.getTaiKhoan().equals(code)) {
					ma = u;
				}
			}
			
			return ma;
			
		}

//SỬA TTTK CỦA USER
		
		@RequestMapping(value="tttk", method = RequestMethod.GET)
		public String suaTTTK(ModelMap model, HttpServletRequest request) {
			if(!taiKhoan.equals(""))
			{
				model.addAttribute("suaTTTK", selectTK(taiKhoan));
				return "user/tttk";
			}else
			{
				return "user/error";
			}
			
				
		}
		
		@RequestMapping(value="tttk", method = RequestMethod.POST)
		public String suaTTTK(@ModelAttribute("suaTTTK") UserEntity BV, ModelMap model, HttpServletRequest request) throws ServletException, IOException{
			Session s = factory.openSession();
			Transaction t = s.beginTransaction();
			String hql = "from UserEntity";
			Query query = s.createQuery(hql);
			List<UserEntity> list = query.list();
			for(UserEntity p : list) {
				if(p.getTaiKhoan().equals(taiKhoan)) {
					try {
						p.setTenND(BV.getTenND());
						p.setDiaChi(BV.getDiaChi());
						p.setSdt(BV.getSdt());
						p.setMatKhau(BV.getMatKhau());
						
						s.update(p);
						t.commit();
						model.addAttribute("message1", "Cập nhật thành công!!");
					} catch (Exception e) {
						
						model.addAttribute("message1", "Cập nhật không thành công!!");
					} finally {
						s.close();
					}
				}
				else {
					p.setTaiKhoan("");
					p.setTenND("");
					p.setDiaChi("");
					p.setSdt("");
					p.setMatKhau("");
				}
			}
			return "redirect:../user/home.htm";
		}
		
		
		
// MAIL QUÊN MẬT KHẨU
		public boolean sendMail(String mailClient, String pass) {
			boolean check = true;
			String body = "Bạn đã ấn quên mật khẩu, đây là mật khẩu tạm thời của bạn:" + pass + ". <br>Bạn hãy dùng mật khẩu này để tiến hành đăng nhập, sau đó vào thay đổi thành mật khẩu khác nếu không muốn bị hack tài khoản.<br> Xin cảm ơn!";
					
			String from = "n17dccn048@student.ptithcm.edu.vn";
			try{
				MimeMessage mail = mailer.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail);
				helper.setFrom(from, from);
				helper.setTo(mailClient);
				helper.setReplyTo(from, from);
				helper.setSubject("Thông báo xác nhận đổi mật khẩu!");
				helper.setText(body, true);

				mailer.send(mail);
			}
			catch(Exception e){
				check = false;
			}
			return check;
		}
		
		
}
