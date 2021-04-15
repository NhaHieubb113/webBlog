package blog.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import blog.entity.*;

@Entity
@Table(name = "BAIVIET")
public class BaiVietEntity {
	@Id@GeneratedValue
	@Column(name = "MaBaiViet")
	private int maBaiViet;
	
	@ManyToOne 
	@JoinColumn(name="MaDanhMuc")
	private DanhMucEntity maDanhMuc;
//	@Column(name = "MaDanhMuc")
//	private String maDanhMuc;
	
	@Column(name = "TieuDe")
	private String tieuDe;
	
	@Column(name = "HinhMinhHoa")
	private String hinhMH;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "ThoiGian")
	private Date thoiGian;
	
	@Column(name = "NoiDung")
	private String noiDung;
	
//	@Column(name = "TaiKhoan")
//	private String taiKhoan;
	@ManyToOne 
	@JoinColumn(name="TaiKhoan")
	private UserEntity taiKhoan;
	
	@OneToMany(mappedBy = "maBaiViet", fetch = FetchType.EAGER)
	private Collection<BinhLuanEntity> binhLuan;

	public int getMaBaiViet() {
		return maBaiViet;
	}

	public void setMaBaiViet(int maBaiViet) {
		this.maBaiViet = maBaiViet;
	}

	public DanhMucEntity getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(DanhMucEntity maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getHinhMH() {
		return hinhMH;
	}

	public void setHinhMH(String hinhMH) {
		this.hinhMH = hinhMH;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public UserEntity getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(UserEntity taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public Collection<BinhLuanEntity> getBinhLuan() {
		return binhLuan;
	}

	public void setBinhLuan(Collection<BinhLuanEntity> binhLuan) {
		this.binhLuan = binhLuan;
	}
	
	
}
