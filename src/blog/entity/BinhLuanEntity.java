package blog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BINHLUAN")
public class BinhLuanEntity {
	@Id@GeneratedValue
	@Column(name = "MaBinhLuan")
	private int maBinhLuan;
	
//	@Column(name = "MaBaiViet")
//	private int maBaiViet;
	@ManyToOne 
	@JoinColumn(name="MaBaiViet")
	private BaiVietEntity maBaiViet;
	
//	@Column(name = "TaiKhoan")
//	private String taiKhoan;
	@ManyToOne 
	@JoinColumn(name="TaiKhoan")
	private UserEntity taiKhoan;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "ThoiGian")
	private Date thoiGian;
	
	@Column(name = "NoiDung")
	private String noiDung;

	
	public UserEntity getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(UserEntity taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public int getMaBinhLuan() {
		return maBinhLuan;
	}

	public void setMaBinhLuan(int maBinhLuan) {
		this.maBinhLuan = maBinhLuan;
	}

	public BaiVietEntity getMaBaiViet() {
		return maBaiViet;
	}

	public void setMaBaiViet(BaiVietEntity maBaiViet) {
		this.maBaiViet = maBaiViet;
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
	
	
	
}
