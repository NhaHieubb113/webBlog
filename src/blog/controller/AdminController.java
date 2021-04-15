package blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import blog.entity.*;
import blog.bean.*;

@Controller
@Transactional
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	Mailer mailer;
UserController uc;

//DS MAIL
	@RequestMapping(value = "/dsMail")
	public String dsMail(ModelMap model) {
		if(uc.taiKhoan.equals("n17dccn048@student.ptithcm.edu.vn")) {
			Session session = factory.getCurrentSession();
			String hql = "FROM MailEntity ";
			Query query = session.createQuery(hql);
			List<MailEntity> list = query.list();
			model.addAttribute("mail", list);
			
			return "admin/dsMail";
		}
		else {
			return "admin/error";
		}

	}
	
//GUI MAIL
	String ma = "";
	@RequestMapping(value="mail", method = RequestMethod.GET)
	public String sendMail(ModelMap model, HttpServletRequest request) {
		ma = request.getParameter("idSendMail");
		model.addAttribute("tka", selectTK(ma));
		return "admin/mail";
	}
	
	@RequestMapping(value = "mail", method = RequestMethod.POST)
	public String sendMail(ModelMap model,@ModelAttribute("tka") UserEntity user,
//			@RequestParam("form") String form,
//			@RequestParam("to")  String to,
			@RequestParam("name") String tieuDe,
			@RequestParam("body") String noiDung, RedirectAttributes redirect, MailEntity mail ) {
		
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "FROM MailEntity";
		Query query = s.createQuery(hql);
		List<MailEntity> list = query.list();
		model.addAttribute("tka", new MailEntity());
		
		try {
			//Gui mail
			mail.setThoiGian(new Date());
			mail.setTuaDe(tieuDe);
			mail.setNoiDung(noiDung);
			mail.setNguoiNhan(selectTK(user.getTaiKhoan()));
			mailer.send(user.getTaiKhoan(), tieuDe, noiDung);
			s.save(mail); //luu do db
			t.commit(); //chap nhan thay doi du lieu trong db
			model.addAttribute("message", "gửi mail thành công");
			
		} catch (Exception e) {
			model.addAttribute("message", "gửi mail thất bại");
		}
		
		return "redirect:/admin/dsMail.htm";
	}

	
	
//QUẢN LÝ DANH MỤC
	
	//ADD DANH MUC
	@RequestMapping(value = "/qldm", method = RequestMethod.GET)
	public String register(ModelMap model) throws ServletException, IOException {
		if(uc.taiKhoan.equals("n17dccn048@student.ptithcm.edu.vn")) {
			Session session = factory.getCurrentSession();
			String hql = "FROM DanhMucEntity";
			Query query = session.createQuery(hql);
			List<DanhMucEntity> list = query.list();
			model.addAttribute("dm", list);
			model.addAttribute("dme", new DanhMucEntity());
			return "admin/qldm";
		}
		else {
			return "admin/error";
		}

	}

	@RequestMapping(value = "qldm", method = RequestMethod.POST)
	public String register(@ModelAttribute("dme") DanhMucEntity dmuc, BindingResult errors, HttpServletRequest request, ModelMap model)
			throws ServletException, IOException {
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		boolean check = true;
		String hql = "FROM DanhMucEntity";
		Query query = s.createQuery(hql);
		List<DanhMucEntity> listAcc = query.list();


		if(dmuc.getTenDanhMuc().trim().length() == 0) {
			errors.rejectValue("tenDanhMuc", "dme", "Vui lòng nhập tên danh mục!");
		}		
		if(dmuc.getMaDanhMuc().trim().length() == 0) {
			errors.rejectValue("maDanhMuc", "dme", "Vui lòng nhập mã danh mục!");
		}
		
		if(errors.hasErrors()) {
			model.addAttribute("message1", "Vui lòng điền thông tin đầy đủ!");
		}
		else {
			for (DanhMucEntity ktra : listAcc) {
				if (dmuc.getMaDanhMuc().equals(ktra.getMaDanhMuc())) {
					check = false;
					errors.rejectValue("maDanhMuc", "dme", "Mã danh mục đã tồn tại!");
				}
			}
			if(check == true) {
				s.save(dmuc); //luu do db
				t.commit(); //chap nhan thay doi du lieu trong db
				model.addAttribute("message", "Thành công");
			}
			else {
				model.addAttribute("message", "Thất bại"
						+ "");
			}
		}
		
		return "redirect:/admin/qldm.htm";
	}
	//UPDATE DANH MUC	
		String code = "";
		@RequestMapping(value="update", method = RequestMethod.GET)
		public String update(ModelMap model, HttpServletRequest request) {
			code = request.getParameter("idUpdate");
			model.addAttribute("dme", selectMaDM(code));
			return "admin/qldm";
		}
		@RequestMapping(value="update", method = RequestMethod.POST)
		public String update(@ModelAttribute("dme") DanhMucEntity dmm, ModelMap model, HttpServletRequest request) throws ServletException, IOException{
				Session s = factory.openSession();
				Transaction t = s.beginTransaction();
				String hql = "from DanhMucEntity";
				Query query = s.createQuery(hql);
				List<DanhMucEntity> list = query.list();
				for(DanhMucEntity p : list) {
					if(p.getMaDanhMuc().equals(code)) {
						try {
							p.setTenDanhMuc(dmm.getTenDanhMuc());
							p.setMaDanhMuc(dmm.getMaDanhMuc());
							s.update(p);
							t.commit();
							model.addAttribute("message1", "Cập nhật thành công!!");
							//return "redirect:/admin/qlsp.htm";
						} catch (Exception e) {
							
							model.addAttribute("message1", "Cập nhật không thành công!!");
//							return "redirect:/admin/qldm.htm";
						} finally {
							s.close();
						}
					}
				}
				
				return "redirect:/admin/qldm.htm";
		}
	
		// DELETE DANH MUC	
		
		@RequestMapping("delete")
		public String del(ModelMap model, HttpServletRequest request, HttpServletResponse rsp) throws ServletException, IOException{

			Session s = factory.openSession();
			Transaction t = s.beginTransaction();
			String code = request.getParameter("id");
			try {
				String hql = "from DanhMucEntity where maDanhMuc LIKE :maDanhMuc";
				Query query = s.createQuery(hql);
				query.setParameter("maDanhMuc", code);
				List<DanhMucEntity> list = query.list();
				for(DanhMucEntity prod: list) {
					s.delete(prod);
					t.commit();
					model.addAttribute("message1", "Thành công");
					rsp.addIntHeader("Refresh", 0); 
				}
				return "redirect:/admin/qldm.htm";
			} catch (Exception e) {
				
				model.addAttribute("message1", "Thất bại");
			} finally {
				s.close();
			}
			
		return "admin/qldm";
		
	}
		
		
		
		
//QUẢN LÝ TÀI KHOẢN
	@RequestMapping(value = "/qlTaiKhoan", method = RequestMethod.GET)
	public String qlTaiKhoan(ModelMap model) {
		if(uc.taiKhoan.equals("n17dccn048@student.ptithcm.edu.vn")) {
			Session session = factory.getCurrentSession();
			String hql = "FROM UserEntity where bitKhoa = 1 and bitQuyen = 0";
			Query query = session.createQuery(hql);
			List<BaiVietEntity> list = query.list();
			model.addAttribute("tk", list);
			
			return "admin/qlTaiKhoan";
		}
		else {
			return "admin/error";
		}

	}

	
	// DELETE TAI KHOAN	CUA USER
	
			@RequestMapping("delete1")
			public String del1(ModelMap model, HttpServletRequest request, HttpServletResponse rsp) throws ServletException, IOException{

				String code = request.getParameter("id");
				Session s = factory.openSession();
				Transaction t = s.beginTransaction();
				String hql = "from UserEntity";
				Query query = s.createQuery(hql);
				List<UserEntity> list = query.list();
				for(UserEntity p : list) {
					if(p.getTaiKhoan().equals(code)) {
						try {
							p.setBitKhoa(0);
							s.update(p);
							t.commit();
							model.addAttribute("message1", true);
						} catch (Exception e) {
							
							model.addAttribute("message1", false);
						} finally {
							s.close();
						}
					}
				}
			
			return "redirect:/admin/qlTaiKhoan.htm";
			
		}

			
			
//HOME BÀI VIẾT	
			@RequestMapping("home")
			public String home(ModelMap model) {
				if(uc.taiKhoan.equals("n17dccn048@student.ptithcm.edu.vn")) {
					Session session = factory.getCurrentSession();
					String hql = "FROM BaiVietEntity";
					Query query = session.createQuery(hql);
					List<BaiVietEntity> list = query.list();
					model.addAttribute("bv", list);
					
					return "admin/home";
				}
				else {
					return "admin/error";
				}
			}
						
			
//THÊM BÀI VIẾT
	
			// modal danh mục
			@ModelAttribute("danhMuc")
			public List<DanhMucEntity> selectMaDM() {
				Session session = factory.getCurrentSession();
				String hql = "FROM DanhMucEntity";
				Query query = session.createQuery(hql);
				@SuppressWarnings("unchecked")
				List<DanhMucEntity> list = query.list();
				return list;
			}
			
			
			@RequestMapping(value = "/themBV", method = RequestMethod.GET)
			public String themBaiViet(ModelMap model) throws ServletException, IOException {
				if(uc.taiKhoan.equals("n17dccn048@student.ptithcm.edu.vn")) {
					model.addAttribute("baiViet", new BaiVietEntity());
					return "admin/themBV";
				}
				else {
					return "admin/error";
				}

			}
			
			@RequestMapping(value="themBV", method = RequestMethod.POST)
			public String themBaiViet(HttpServletResponse rsp, @ModelAttribute("baiViet") BaiVietEntity baiViet, BindingResult errors, @RequestParam(value="hinhMH") MultipartFile img1, ModelMap model, HttpServletRequest request)
					throws ServletException, IOException{
				Session s = factory.openSession();
				Transaction t = s.beginTransaction();
				
//				if(baiViet.getTieuDe().trim().length() == 0) {
//					errors.rejectValue("tieuDe", "baiViet", "Vui lòng nhập tiêu đề!");
//				}		
//				if(baiViet.getNoiDung().trim().length() == 0) {
//					errors.rejectValue("noiDung", "baiViet", "Vui lòng nhập nội dung!");
//				}
//				
//				if(errors.hasErrors()) {
//					model.addAttribute("message", "Vui lòng điền thông tin đầy đủ!");
//				}
//				else {
					try {
						saveImage(img1);
						String ten = img1.getOriginalFilename();
						baiViet.setHinhMH(ten);
						baiViet.setThoiGian(new Date());
						baiViet.setHinhMH(img1.getOriginalFilename());
						baiViet.setTaiKhoan(selectTK("n17dccn048@student.ptithcm.edu.vn"));
						s.save(baiViet);
						t.commit();
						model.addAttribute("message", "Thành công");

					}
					catch(Exception e) {
						t.rollback();
						model.addAttribute("message", "Thất bại");
					}
					finally {
						s.close();
					}
//				}
				
				return "admin/themBV";
			}


	
	// SAVE

			public void saveImage(MultipartFile image) {
				try {
					image.transferTo(new File(
							"C:\\Users\\Admin\\eclipse-workspace\\webBlog\\WebContent\\imgs\\"
									+ image.getOriginalFilename()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//ds bai viet
			@ModelAttribute("bv")
			public List<BaiVietEntity> selectProd() {
				
				Session session = factory.getCurrentSession();
				String hql1 = "FROM BaiVietEntity";
				Query query1 = session.createQuery(hql1);
				List<BaiVietEntity> list1 = query1.list();
				return list1;
			}
			
			//SELECT BÀI VIẾT
			public BaiVietEntity selectProd(int code) {
				BaiVietEntity prod =null;
				Session session = factory.getCurrentSession();
				String hql = "from BaiVietEntity";
				Query query = session.createQuery(hql);
				List<BaiVietEntity> list = query.list();
				for(BaiVietEntity u: list) {
					if(u.getMaBaiViet() == code) {
						prod = u;
					}
				}
				return prod;

			}
			
			//SỬA BÀI VIẾT
			int codeBV = 0;
			@RequestMapping(value="suaBV", method = RequestMethod.GET)
			public String suaBV(ModelMap model, HttpServletRequest request) {

				codeBV = Integer.parseInt(request.getParameter("idUpdate"));
				model.addAttribute("Suabv", selectProd(codeBV));
				
				return "admin/suaBV";
					
			}
			
			@RequestMapping(value="suaBV", method = RequestMethod.POST)
			public String suaBV(@ModelAttribute("Suabv") BaiVietEntity BV,  BindingResult errors, @RequestParam(value="hinh") MultipartFile img, ModelMap model, HttpServletRequest request) throws ServletException, IOException{
				System.out.print("aBChBHF");
				Session s = factory.openSession();
				Transaction t = s.beginTransaction();
					String hql = "from BaiVietEntity";
					Query query = s.createQuery(hql);
					List<BaiVietEntity> list = query.list();
					for(BaiVietEntity p : list) {
						if(p.getMaBaiViet() == codeBV) {
							try {
								if(!img.isEmpty()) {
									saveImage(img);
									p.setHinhMH(img.getOriginalFilename());
								}else{
									p.setHinhMH(p.getHinhMH());
								}
								p.setTieuDe(BV.getTieuDe());
								p.setMaDanhMuc(BV.getMaDanhMuc());
								p.setNoiDung(BV.getNoiDung());
								p.setThoiGian(new Date());

								s.update(p);
								t.commit();
								model.addAttribute("message1", "Cập nhật thành công!!");
								return "redirect:/admin/home.htm";
							} catch (Exception e) {
								
								model.addAttribute("message1", "Cập nhật không thành công!!");
								return "redirect:/admin/home.htm";
							} finally {
								s.close();
							}
						}
					}
				
				
				
				return "admin/home";
			}
			
			//XÓA BÀI VIẾT
			
			@RequestMapping("delete_bv")
			public String del_bv(ModelMap model, HttpServletRequest request, HttpServletResponse rsp) throws ServletException, IOException{

				Session s = factory.openSession();
				Transaction t = s.beginTransaction();
				int code =  Integer.parseInt(request.getParameter("id"));
				try {
					String hql = "from BaiVietEntity where maBaiViet LIKE :maBaiViet";
					Query query = s.createQuery(hql);
					query.setParameter("maBaiViet", code);
					List<BaiVietEntity> list = query.list();
					for(BaiVietEntity bv: list) {
						s.delete(bv);
						t.commit();
						model.addAttribute("message1", "Thành công");
						rsp.addIntHeader("Refresh", 0); 
					}
					return "redirect:/admin/home.htm";
				} catch (Exception e) {
					
					model.addAttribute("message1", "Thất bại");
				} finally {
					s.close();
				}

			return "admin/home";
			
		}
	
//THONG TIN TÀI KHOẢN

	//UPDATE TAI KHOAN CUA ADMIN
	@RequestMapping(value="tttk", method = RequestMethod.GET)
	public String suaTTTK(ModelMap model, HttpServletRequest request) {

		if(uc.taiKhoan.equals("n17dccn048@student.ptithcm.edu.vn")) {
			model.addAttribute("SuaTTTK", selectTK("n17dccn048@student.ptithcm.edu.vn"));
			return "admin/tttk";
				
		}
		else {
			return "admin/error";
		}
		
	}
	
	@RequestMapping(value="tttk", method = RequestMethod.POST)
	public String suaTTTK(@ModelAttribute("SuaTTTK") UserEntity BV, BindingResult errors, ModelMap model, HttpServletRequest request) throws ServletException, IOException{
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		String hql = "from UserEntity";
		Query query = s.createQuery(hql);
		List<UserEntity> list = query.list();
		for(UserEntity p : list) {
			if(p.getTaiKhoan().equals("n17dccn048@student.ptithcm.edu.vn")) {
				try {
					p.setTenND(BV.getTenND());
					p.setDiaChi(BV.getDiaChi());
					p.setSdt(BV.getSdt());
					p.setMatKhau(BV.getMatKhau());
					
					s.update(p);
					t.commit();
					model.addAttribute("message1", "Cập nhật thành công!!");
					return "redirect:/admin/tttk.htm";
				} catch (Exception e) {
					
					model.addAttribute("message1", "Cập nhật không thành công!!");
					return "redirect:/admin/tttk.htm";
				} finally {
					s.close();
				}
			}
		}
		return "admin/tttk";
	}
	

//QUẢN LÝ BÌNH LUẬN
	//hien thi ds
	@RequestMapping("qlBinhLuan")
	public String qlBinhLuan(ModelMap model) {
		if(uc.taiKhoan.equals("n17dccn048@student.ptithcm.edu.vn")) {
			Session session = factory.getCurrentSession();
			String hql = "FROM BinhLuanEntity";
			Query query = session.createQuery(hql);
			List<BinhLuanEntity> list = query.list();
			model.addAttribute("bl", list);
			
			return "admin/qlBinhLuan";
		}
		else {
			return "admin/error";
		}

	}
	
	//DELETE BINH LUAN
	@RequestMapping("delete2")
	public String del2(ModelMap model, HttpServletRequest request, HttpServletResponse rsp) throws ServletException, IOException{
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		int code = Integer.parseInt(request.getParameter("id"));
		try {
			String hql = "from BinhLuanEntity where maBinhLuan LIKE :maBinhLuan";
			Query query = s.createQuery(hql);
			query.setParameter("maBinhLuan", code);
			List<BinhLuanEntity> list = query.list();
			for(BinhLuanEntity prod: list) {
				s.delete(prod);
				t.commit();
				model.addAttribute("message1", "Thành công");
				rsp.addIntHeader("Refresh", 0); 
			}
//			return "redirect:/admin/qlBinhLuan.htm";
		} catch (Exception e) {
			
			model.addAttribute("message1", "Xóa thất bại");
		} finally {
			s.close();
		}
		
		//load laij danh sachs
		Session session = factory.getCurrentSession();
		String hql1 = "FROM BinhLuanEntity";
		Query query1 = session.createQuery(hql1);
		List<BinhLuanEntity> list = query1.list();
		model.addAttribute("bl", list);
		
	return "admin/qlBinhLuan";
	
}
	
	//SELECT MA DANH MUC
	public DanhMucEntity selectMaDM(String code) {
		
		DanhMucEntity ma =null;
		Session session = factory.getCurrentSession();
		String hql = "from DanhMucEntity";
		Query query = session.createQuery(hql);
		List<DanhMucEntity> list = query.list();
		for(DanhMucEntity u: list) {
			if(u.getMaDanhMuc().equals(code)) {
				ma = u;
			}
		}
		
		return ma;
		
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
	

	}
