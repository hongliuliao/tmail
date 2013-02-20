/**
 * 
 */
package org.tmail.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;
import org.tmail.model.Account;
import org.tmail.model.Attachment;
import org.tmail.model.MailIntroduction;
import org.tmail.model.Pagination;
import org.tmail.model.TMail;
import org.tmail.utils.IReceiveHandler;
import org.tmail.utils.MailTemplate;


/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午6:47:23
 */
@Repository
public class MailDao {

	private static final Log log = LogFactory.getLog(MailDao.class);
	
	public Pagination<MailIntroduction> getMailIntroductions(Account account, final int start, final int count){
		MailTemplate mailTemplate = new MailTemplate(account);
		return mailTemplate.receive(new IReceiveHandler() {
			
			@Override
			public Pagination<MailIntroduction> handler(Folder folder) throws Exception {
				List<MailIntroduction> mailIntroductions = new ArrayList<MailIntroduction>();
				int total = folder.getMessageCount();
				Pagination<MailIntroduction> pagination = new Pagination<MailIntroduction>(start, count);
				pagination.setTotalCount(total);
				pagination.setResultList(mailIntroductions);
				if(total == 0 || start + 1 > total) {
					return pagination;
				}
				int fetchCount = count;
				if(count > total) {
					fetchCount = total;
				}
				if(start + fetchCount > total) {
					fetchCount = total - start;
				}
				for(int i=start;i<start + fetchCount;i++) {
					Message message = folder.getMessage(total - i);
					mailIntroductions.add(MailIntroduction.fromMessage(message));
				}
				return pagination;
			}
		});
		
	}
	
	public void removeMails(Account account, final int[] msgnums) {
		MailTemplate mailTemplate = new MailTemplate(account);
		mailTemplate.receive(new IReceiveHandler() {

			@Override
			public Object handler(Folder folder) throws Exception {
				for (int msgnum : msgnums) {
					Message message = folder.getMessage(msgnum);
					if(message != null) {
						message.setFlag(Flag.DELETED, true);
					}
				}
				return null;
			}
		});
	}
	
	public TMail getTMail(Account account, final int msgnum) {
		MailTemplate mailTemplate = new MailTemplate(account);
		return mailTemplate.receive(new IReceiveHandler() {
			
			@Override
			public Object handler(Folder folder) throws Exception {
				Message message = folder.getMessage(msgnum);
				if(message == null) {
					return null;
				}
				TMail mail = new TMail();
				mail.setMailIntroduction(MailIntroduction.fromMessage(message));
				if(message.getContent() instanceof String) {
					mail.setContext((String) message.getContent());
				}
				if(message.getContent() instanceof Multipart) {
					Multipart multipart = (Multipart)message.getContent();
					parseMultipart(mail, multipart);
				}
				return mail;
			}
		});
	}
	
	/** 
     * 对复杂邮件的解析 
     * @param multipart 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static void parseMultipart(TMail mail, Multipart multipart) throws MessagingException, IOException {  
        int count = multipart.getCount();  
        for (int idx=0;idx<count;idx++) {  
            BodyPart bodyPart = multipart.getBodyPart(idx);  
            if (bodyPart.isMimeType("text/plain")) {  
            	mail.addContext((String)bodyPart.getContent());  
            } else if(bodyPart.isMimeType("text/html")) {  
            	mail.addHtmlContext((String)bodyPart.getContent());  
            } else if(bodyPart.isMimeType("multipart/*")) {  
                Multipart mpart = (Multipart)bodyPart.getContent(); 
                parseMultipart(mail, mpart); 
            } else if(bodyPart.getContentType().startsWith("application/")) {
            	String disposition = bodyPart.getDisposition();
            	if (disposition.equalsIgnoreCase(BodyPart.ATTACHMENT) || disposition.equalsIgnoreCase(BodyPart.INLINE)) {  
                    String fileName = bodyPart.getFileName();  
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {    
                        fileName = MimeUtility.decodeText(fileName);    
                    }
                    mail.addAttachment(new Attachment(fileName, bodyPart.getInputStream(), bodyPart.getContentType()));
                }
            }
        }  
    }  
	
	public int countNewMail(Account account, final int lastMessageNumber) {
		MailTemplate mailTemplate = new MailTemplate(account);
		Integer newMailCount = mailTemplate.receive(new IReceiveHandler() {
			
			@Override
			public Integer handler(Folder folder) throws Exception {
				if(folder.getMessageCount() == 0 || lastMessageNumber >= folder.getMessageCount()) {
					return 0;
				}
				return folder.getMessageCount() - lastMessageNumber;
			}
		});
		return newMailCount.intValue();
	}
	
	public List<MailIntroduction> getNewMail(final Account account, final int lastMessageNumber) {
		MailTemplate mailTemplate = new MailTemplate(account);
		return mailTemplate.receive(new IReceiveHandler() {
			
			@Override
			public Object handler(Folder folder) throws Exception {
				List<MailIntroduction> mailIntroductions = new ArrayList<MailIntroduction>();
				int total = folder.getMessageCount();
				if(total == 0 || lastMessageNumber >= total) {
					return Collections.emptyList();
				}
				account.setLastMessageNum(total);
				int fetchCount = total - lastMessageNumber;
				if(fetchCount > 10) {
					fetchCount = 10;
					log.warn("Too big fetchCount:" + fetchCount);
				}
				for(int i=lastMessageNumber;i<=lastMessageNumber + fetchCount;i++) {
					Message message = folder.getMessage(i);
					MailIntroduction mailIntroduction = new MailIntroduction();
					if(message.getFrom().length != 0) {
						mailIntroduction.setFrom(((InternetAddress)message.getFrom()[0]).getAddress());
					}
					if(message.getFrom().length != 1) {
						log.warn("Have many senders which message from:" + Arrays.toString(message.getFrom()));
					}
					mailIntroduction.setPersonal(((InternetAddress)message.getFrom()[0]).getPersonal());
					mailIntroduction.setSentDate(message.getSentDate());
					mailIntroduction.setSubject(message.getSubject());
					mailIntroduction.setMessageNumber(message.getMessageNumber());
					mailIntroductions.add(mailIntroduction);
				}
				return mailIntroductions;
			}
		});
	}
	
	public void sendMail(Account account, String toMail, String subject, String context) {
		JavaMailSenderImpl mailSender = createSender(account);
		SimpleMailMessage message = createMessage(account, toMail, subject, context);
		mailSender.send(message);
	}

	/**
	 * @param account
	 * @param replyTo
	 * @param subject
	 * @param context
	 * @return
	 */
	private SimpleMailMessage createMessage(Account account, String replyTo,
			String subject, String context) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(account.getEmail());
		message.setReplyTo(replyTo);
		message.setSentDate(new Date());
		message.setSubject(subject);
		message.setText(context);
		message.setTo(replyTo);
		return message;
	}

	/**
	 * @param account
	 * @return
	 */
	private JavaMailSenderImpl createSender(Account account) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("utf-8");
		mailSender.setHost(account.getSmtpHost());
		mailSender.setPassword(account.getPassword());
		mailSender.setUsername(account.getEmail());
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.timeout", "25000");
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	
	
}
