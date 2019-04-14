package com.zlw.selenium;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendEmailTest {

	public static void fun2()throws AddressException,MessagingException,IOException{
		Properties prop=new Properties();
		prop.setProperty("mail.host", "smtp.163.com");
		prop.setProperty("mail.smtp.auth", "true");
		Authenticator auth=new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("z1104031082@163.com","zlw092410" );
			}
		};
		Session session = Session.getInstance(prop, auth);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("z1104031082@163.com"));
		msg.setRecipients(RecipientType.TO,"1104031082@qq.com");
		msg.setSubject("测试邮件");//标题
		MimeMultipart list = new MimeMultipart();
		MimeBodyPart part1 = new MimeBodyPart();
		part1.setContent("啦啦啦！", "text/html;charset=utf-8"); //内容
		list.addBodyPart(part1);
		MimeBodyPart part2 = new MimeBodyPart();
		part2.attachFile("E:\\123.png");//附件
		part2.setFileName(MimeUtility.encodeText("title_en.png"));
		list.addBodyPart(part2);
		msg.setContent(list);
		Transport.send(msg);
	}
	
	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		
		fun2();

	}

}
