/**
 * 
 */
package org.tmail.utils;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tmail.model.Account;


/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午1:58:43
 */
public class MailTemplate {
	
	private static final Log log = LogFactory.getLog(MailTemplate.class);
	
	private Account userInfo;
	
	private String pop3Host;
	
	/**
	 * @param userInfo
	 * @param pop3Host
	 */
	public MailTemplate(Account userInfo, String pop3Host) {
		super();
		this.userInfo = userInfo;
		this.pop3Host = pop3Host;
	}

	@SuppressWarnings("unchecked")
	public <T> T receive(IReceiveHandler receiveHandler) {
		Folder folder = null;
		Store store = null;
		try {
			Session session = Session.getInstance(this.getMailProperties(pop3Host));
			store = session.getStore("pop3");
			store.connect(userInfo.getEmail(), userInfo.getEmailPassword()); 
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			int total = folder.getMessageCount();
			if(total == 0) {
				return null;
			}
			return (T) receiveHandler.handler(folder);
		} catch (Exception e) {
			throw new RuntimeException("Receive mail fail", e);
		} finally {
			try {
				if (folder != null)  {
					folder.close(true);   
				}
			    if (store != null)  {
			    	store.close();  
			    }
			} catch (Exception e2) {
				log.error("Close folder or store error!");
			}
		}
	}
	
	private Properties getMailProperties(String pop3Host) {
		Properties prop = System.getProperties();  
        prop.put("mail.store.protocol", "pop3");  
        prop.put("mail.pop3.host", pop3Host); 
        return prop;
	}
	
}
