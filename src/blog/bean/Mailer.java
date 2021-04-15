package blog.bean;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import blog.entity.DanhMucEntity;
import blog.entity.MailEntity;


@Service("mailer")
public class Mailer  {
	@Autowired
	JavaMailSender mailer;
	@Autowired
	SessionFactory factory;
	String from="n17dccn048@student.ptithcm.edu.vn";
	//Gửi mail cá nhân
	public void send(String to, String subject, String body){
		
		try {
			MimeMessage mail = mailer.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailer.send(mail);
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// Gửi đến toàn bộ user
public void sendAll(String name, List<String> to, String subject,String body){
		String[] toAll = to.toArray(new String[0]);
		try {
			MimeMessage mail = mailer.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(from, name);
			helper.setBcc(toAll);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailer.send(mail);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
