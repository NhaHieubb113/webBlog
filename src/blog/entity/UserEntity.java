package blog.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NGUOIDUNG")
public class UserEntity {
	@Id
	@Column(name = "TaiKhoan")
	private String taiKhoan;
	
	@Column(name = "BitQuyen")
	private int bitQuyen;
	
	@Column(name = "BitKhoa")
	private int bitKhoa;
	
	@Column(name = "TenND")
	private String tenND;
	
	@Column(name = "MatKhau")
	private String matKhau;
	
	@Column(name = "SDT")
	private String sdt;
	
	@Column(name = "DiaChi")
	private String diaChi;
	
//	@OneToMany(mappedBy = "taiKhoan", fetch = FetchType.EAGER)
//	private Collection<DanhMucEntity> danhMuc;
	
	@OneToMany(mappedBy = "taiKhoan", fetch = FetchType.EAGER)
	private Collection<BaiVietEntity> baiViet;
	
	@OneToMany(mappedBy = "taiKhoan", fetch = FetchType.EAGER)
	private Collection<BinhLuanEntity> binhLuan;

	@OneToMany(mappedBy = "nguoiNhan", fetch = FetchType.EAGER)
	private Collection<MailEntity> mail;
	
	public Collection<BinhLuanEntity> getBinhLuan() {
		return binhLuan;
	}

	public void setBinhLuan(Collection<BinhLuanEntity> binhLuan) {
		this.binhLuan = binhLuan;
	}

	public Collection<MailEntity> getMail() {
		return mail;
	}

	public void setMail(Collection<MailEntity> mail) {
		this.mail = mail;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public int getBitQuyen() {
		return bitQuyen;
	}

	public void setBitQuyen(int bitQuyen) {
		this.bitQuyen = bitQuyen;
	}

	public int getBitKhoa() {
		return bitKhoa;
	}

	public void setBitKhoa(int bitKhoa) {
		this.bitKhoa = bitKhoa;
	}

	public String getTenND() {
		return tenND;
	}

	public void setTenND(String tenND) {
		this.tenND = tenND;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Collection<BaiVietEntity> getBaiViet() {
		return baiViet;
	}

	public void setBaiViet(Collection<BaiVietEntity> baiViet) {
		this.baiViet = baiViet;
	}

	
}
