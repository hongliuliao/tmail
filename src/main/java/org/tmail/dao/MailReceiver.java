/**
 * 
 */
package org.tmail.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tmail.model.MailIntroduction;
import org.tmail.model.Account;
import org.tmail.utils.IReceiveHandler;
import org.tmail.utils.MailTemplate;


/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午6:47:23
 */
public class MailReceiver {

	private static final Log log = LogFactory.getLog(MailReceiver.class);
	
	private String userName;
	
	private String password;
	
	private String pop3Host;
	
	MailTemplate mailTemplate;
	/**
	 * @param userName
	 * @param password
	 * @param pop3Host
	 */
	public MailReceiver(String userName, String password, String pop3Host) {
		super();
		this.userName = userName;
		this.password = password;
		this.pop3Host = pop3Host;
		mailTemplate = new MailTemplate(new Account(userName, password), pop3Host);
	}

	
	
	public List<MailIntroduction> getRecentMailIntroductions(final int count){
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
	
	public int countNewMail(final int lastMessageNumber) {
		return mailTemplate.receive(new IReceiveHandler() {
			
			@Override
			public Integer handler(Folder folder) throws Exception {
				if(folder.getMessageCount() == 0 || lastMessageNumber >= folder.getMessageCount()) {
					return 0;
				}
				return folder.getMessageCount() - lastMessageNumber;
			}
		});
	}
	
	public List<MailIntroduction> getNewMail(int lastMessageNumber) {
		List<MailIntroduction> mailIntroductions = new ArrayList<MailIntroduction>();
		try {
			Session session = Session.getInstance(this.getMailProperties());
			Store store = session.getStore("pop3");
			store.connect(userName, password); 
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
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
			
			if (folder != null)  {
				folder.close(true);   
			}
		    if (store != null)  {
		    	store.close();  
		    }
		} catch (Exception e) {
			log.error("Receive mail fail", e);
		}
		return mailIntroductions;
	}
	
	private Properties getMailProperties() {
		Properties prop = System.getProperties();  
        prop.put("mail.store.protocol", "pop3");  
        prop.put("mail.pop3.host", pop3Host); 
        return prop;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the imapHost
	 */
	public String getPop3Host() {
		return pop3Host;
	}

	/**
	 * @param imapHost the imapHost to set
	 */
	public void setPop3Host(String imapHost) {
		this.pop3Host = imapHost;
	}
	
}
