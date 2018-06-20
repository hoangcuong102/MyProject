package jp.co.cyms.base.email;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;

/**
 * Send Email Util
 * 
 * @author AnhNT02
 * @since 2018/01/23
 */
public class EmailUtil {
	public static final Logger LOG = Logger.getLogger(EmailUtil.class);

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param fromEmail
	 * @param toEmail
	 * @param titleFromEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			createContentForMessage(msg, session, toEmail, subject, body);
			LOG.info("Message is ready");
			Transport.send(msg);
			LOG.info("EMail Sent Successfully!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {

		}
	}

	/**
	 * Attach multiple files to an email using JavaMail
	 * 
	 * @param multipart
	 * @param filename
	 */
	private static void addAttachment(Multipart multipart, String quoteDir, String filename) {
		try {
			DataSource source = new FileDataSource(quoteDir + "\\" + filename);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to send email with attachment
	 * 
	 * @param session
	 * @param fromEmail
	 * @param toEmail
	 * @param titleFromEmail
	 * @param subject
	 * @param body
	 */
	public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body,
			ArrayList<String> fileAttachment) throws MessagingException{
		/*try {*/
			MimeMessage msg = new MimeMessage(session);
			createContentForMessage(msg, session, toEmail, subject, body);

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			// messageBodyPart.setText(body);
			messageBodyPart.setContent(body, "text/html; charset=utf-8");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is attachment
			if (fileAttachment.size() > 0) {
				String quoteDir = (String) session.getProperties().getProperty("quoteDir");
				for (String fileName : fileAttachment) {
					addAttachment(multipart, quoteDir, fileName);
				}
			}
			// Send the complete message parts
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			LOG.info("EMail Sent Successfully with attachment!!");
		/*} catch (MessagingException e) {
			e.printStackTrace();
		}*/
	}

	/**
	 * Utility method to send image in email body
	 * 
	 * @param session
	 * @param fromEmail
	 * @param toEmail
	 * @param titleFromEmail
	 * @param subject
	 * @param body
	 */
	public static void sendImageEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			createContentForMessage(msg, session, toEmail, subject, body);

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			messageBodyPart = new MimeBodyPart();
			String filename = "image.png";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "image_id");
			multipart.addBodyPart(messageBodyPart);

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h1>Attached Image</h1>" + "<img src='cid:image_id'>", "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Set the multipart message to the email message
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			LOG.info("EMail Sent Successfully with image!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create session for send mail
	 * 
	 * @param address
	 * @param password
	 * @param SMTPHost
	 * @param TLSPort
	 * 
	 * @Return session
	 */
	public static Session createSession(Properties props, ConfigBean configBean) {
		props = loadProperties(props, configBean);

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configBean.getAddress(), configBean.getPasswordAddress());
			}
		};
		Session session = Session.getInstance(props, auth);
		return session;
	}

	/**
	 * Load Properties
	 * 
	 * @Return Properties
	 */
	public static Properties loadProperties(Properties props, ConfigBean configBean) {
		if (configBean != null) {
			if (!StringUtil.isNullOrEmpty(configBean.getAddress())) {
				props.setProperty("mail.smtp.from", configBean.getAddress());
			} else {
				props.setProperty("mail.smtp.from", "");
			}
			props.setProperty("mail.smtp.host", configBean.getHostAddress());
			props.setProperty("mail.smtp.ssl.trust", configBean.getHostAddress());
			props.setProperty("mail.smtp.port", configBean.getPortAddress());
			props.setProperty("mail.smtp.socketFactory.port", configBean.getPortAddress());
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("quoteDir", configBean.getQuoteDir());
		}
		return props;
	}

	/**
	 * Create Content For Message
	 * 
	 * @throws MessagingException
	 * 
	 * @Return Properties
	 */
	private static MimeMessage createContentForMessage(MimeMessage msg, Session session, String toEmail, String subject,
			String body) {
		// set message headers
		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(session.getProperty("mail.smtp.from")));

			msg.setReplyTo(InternetAddress.parse(session.getProperty("mail.smtp.from"), false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			return msg;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Check format of email
	 * 
	 * @param session
	 * @param toEmail
	 * @throws AddressException
	 * 
	 */
	public static String checkValidation(Session session, String toEmail) throws Exception {
		// Longnd 13_apr_2018 get list email valid
		/*try {
			// Check format mail sender
			InternetAddress[] toEmailAddr = InternetAddress.parse(toEmail, false);
			for (InternetAddress internetAddress : toEmailAddr) {
				internetAddress.validate();
			}
			// Check format mail sender
			InternetAddress fromEmailAddr = new InternetAddress(session.getProperty("mail.smtp.from"));
			fromEmailAddr.validate();
		} catch (AddressException ex) {
			throw ex;
		}*/
		
		List<String> listEmailValid = new LinkedList<>();
		// Check format mail sender
		InternetAddress[] toEmailAddr = null;
		try {
			toEmailAddr = InternetAddress.parse(toEmail, false);
		} catch (Exception e) {
			LOG.info(e);
		}
		for (InternetAddress internetAddress : toEmailAddr) {
			try {
				internetAddress.validate();
				listEmailValid.add(internetAddress.toString());
				LOG.info("email valid " + internetAddress.toString());

			} catch(AddressException e) {
				LOG.info("email Invalid " + internetAddress.toString());
			} catch (Exception e) {
				LOG.info(e);
			}
			
		}
		
		return String.join(",", listEmailValid); 
		
	}

	/**
	 * Read email from html
	 * 
	 * @param filePath
	 * @param input
	 * @throws Exception
	 * 
	 */
	public static String readEmailFromHtml(String filePath, Map<String, String> input) {
		String msg = readContentFromFile(filePath);
		try {
			Set<Entry<String, String>> entries = input.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * Method to read HTML file as a String
	 * 
	 * @param fileName
	 * @throws AddressException
	 * 
	 */
	private static String readContentFromFile(String fileName) {
		StringBuffer contents = new StringBuffer();
		try {
			// use buffering, reading one line at a time
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			try {
				String line = null;
				while ((line = reader.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				reader.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return contents.toString();
	}
}
