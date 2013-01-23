/**
 * 
 */
package org.tmail.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
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
	
	public List<MailIntroduction> getRecentMailIntroductions(Account account, final int count){
		MailTemplate mailTemplate = new MailTemplate(account, "pop.163.com");
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
				for(int i=0;i<fetchCount;i++) {
					Message message = folder.getMessage(total - i);
					mailIntroductions.add(MailIntroduction.fromMessage(message));
				}
				return mailIntroductions;
			}
		});
		
	}
	
	public TMail getTMail(Account account, final int msgnum) {
		MailTemplate mailTemplate = new MailTemplate(account, "pop.163.com");
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
				return mail;
			}
		});
	}
	
	public int countNewMail(Account account, final int lastMessageNumber) {
		MailTemplate mailTemplate = new MailTemplate(account, "pop.163.com");
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
		MailTemplate mailTemplate = new MailTemplate(account, "pop.163.com");
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
