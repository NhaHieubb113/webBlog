package blog.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import blog.entity.*;

@Entity
@Table(name = "DANHMUC")
public class DanhMucEntity {
	@Id
	@Column(name = "MaDanhMuc")
	private String maDanhMuc;
	
	@Column(name = "TenDanhMuc")
	private String tenDanhMuc;
	
//	@Column(name = "TaiKhoan")
//	private String taiKhoan;
//	@ManyToOne 
//	@JoinColumn(name="TaiKhoan")
//	private UserEntity taiKhoan;
	
	@OneToMany(mappedBy = "maDanhMuc", fetch = FetchType.EAGER)
	private Collection<BaiVietEntity> baiViet;
	
	public String getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(String maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public Collection<BaiVietEntity> getBaiViet() {
		return baiViet;
	}

	public void setBaiViet(Collection<BaiVietEntity> baiViet) {
		this.baiViet = baiViet;
	}
	
	
}
