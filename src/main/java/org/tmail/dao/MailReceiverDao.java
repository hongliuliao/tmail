/**
 * 
 */
package org.tmail.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.tmail.model.Account;
import org.tmail.model.MailIntroduction;
import org.tmail.model.TMail;
import org.tmail.utils.IReceiveHandler;
import org.tmail.utils.MailTemplate;


/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午6:47:23
 */
@Repository
public class MailReceiverDao {

	private static final Log log = LogFactory.getLog(MailReceiverDao.class);
	
	public List<MailIntroduction> getMailIntroductions(Account account, final int start, final int count){
		MailTemplate mailTemplate = new MailTemplate(account);
		return mailTemplate.receive(new IReceiveHandler() {
			
			@Override
			public List<MailIntroduction> handler(Folder folder) throws Exception {
				List<MailIntroduction> mailIntroductions = new ArrayList<MailIntroduction>();
				int total = folder.getMessageCount();
				if(total == 0) {
					return mailIntroductions;
				}
				int fetchCount = count;
				if(count > total) {
					fetchCount = total;
				}
				for(int i=start;i<start + fetchCount;i++) {
					Message message = folder.getMessage(total - i);
					mailIntroductions.add(MailIntroduction.fromMessage(message));
				}
				return mailIntroductions;
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
					StringBuilder context = new StringBuilder();
					StringBuilder htmlContext = new StringBuilder();
					parseMultipart(context, htmlContext, multipart);
					mail.setContext(context.toString());
					mail.setHtmlContext(htmlContext.toString());
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
    public static void parseMultipart(StringBuilder context, StringBuilder htmlContext, Multipart multipart) throws MessagingException, IOException {  
        int count = multipart.getCount();  
        for (int idx=0;idx<count;idx++) {  
            BodyPart bodyPart = multipart.getBodyPart(idx);  
            if (bodyPart.isMimeType("text/plain")) {  
            	context.append(bodyPart.getContent());  
            } else if(bodyPart.isMimeType("text/html")) {  
            	htmlContext.append(bodyPart.getContent());  
            } else if(bodyPart.isMimeType("multipart/*")) {  
                Multipart mpart = (Multipart)bodyPart.getContent();  
                parseMultipart(context, htmlContext, mpart);  
            } else if (bodyPart.isMimeType("application/octet-stream")) {  
                String disposition = bodyPart.getDisposition();  
                if (disposition.equalsIgnoreCase(BodyPart.ATTACHMENT) || disposition.equalsIgnoreCase(BodyPart.INLINE)) {  
                    String fileName = bodyPart.getFileName();  
                    context.append("[附件]:" + fileName);
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
	
	public List<MailIntroduction> getNewMail(Account account, final int lastMessageNumber) {
		MailTemplate mailTemplate = new MailTemplate(account);
		return mailTemplate.receive(new IReceiveHandler() {
			
			@Override
			public Object handler(Folder folder) throws Exception {
				List<MailIntroduction> mailIntroductions = new ArrayList<MailIntroduction>();
				int total = folder.getMessageCount();
				if(total == 0 || lastMessageNumber >= total) {
					return Collections.emptyList();
				}
				int fetchCount = total - lastMessageNumber;
				if(fetchCount > 10) {
					fetchCount = 10;
					log.warn("Too big fetchCount:" + fetchCount);
				}
				for(int i=lastMessageNumber;i<lastMessageNumber + fetchCount;i++) {
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

	
}
