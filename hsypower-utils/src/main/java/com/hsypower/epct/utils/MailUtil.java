package com.hsypower.epct.utils;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MailUtil {
	// logger
	protected static final Log log = LogFactory.getLog(MailUtil.class);

	final static MessageFormat FORMAT = new MessageFormat("");

	/**
	 * 发送邮件接口
	 * 
	 * @param subject
	 *            邮件主题
	 * @param to
	 *            收件人地址
	 * @exception MessagingException
	 *                邮件过程中异常
	 */
	public static void send(String to, String subject, String message) {

		boolean sessionDebug = false;
		InternetAddress[] address = null;

		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", Constant.MAIL_SERVER);
			props.put("mail.smtp.auth", "true");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(Constant.MAIL_ADDRESS));

			address = InternetAddress.parse(to, false);
			msg.setRecipients(Message.RecipientType.TO, address);

			msg.setSubject(subject);

			msg.setSentDate(new Date());

			msg.setContent(message, "text/html;charset=UTF-8");

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(Constant.MAIL_SERVER, Constant.MAIL_USERNAME, Constant.MAIL_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			log.error(e);
		}
	}

}
