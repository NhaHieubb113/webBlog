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

@Entity
@Table(name = "MAIL")
public class MailEntity {
	@Id@GeneratedValue
	@Column(name = "Ma")
	private int maMail;
	
//	@Column(name = "NguoiNhan")
//	private String nguoiNhan;
	@ManyToOne 
	@JoinColumn(name="NguoiNhan")
	private UserEntity nguoiNhan;
	
	@Column(name = "TuaDe")
	private String tuaDe;
	
	@Column(name = "NoiDung")
	private String noiDung;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "ThoiGian")
	private Date thoiGian;

	public UserEntity getNguoiNhan() {
		return nguoiNhan;
	}

	public void setNguoiNhan(UserEntity nguoiNhan) {
		this.nguoiNhan = nguoiNhan;
	}

	public int getMaMail() {
		return maMail;
	}

	public void setMaMail(int maMail) {
		this.maMail = maMail;
	}

	public String getTuaDe() {
		return tuaDe;
	}

	public void setTuaDe(String tuaDe) {
		this.tuaDe = tuaDe;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	
}
