/**
 * 
 */
package org.tmail.model;

import java.util.Arrays;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 上午10:41:07
 */
public class MailIntroduction {
	
	private static Log log = LogFactory.getLog(MailIntroduction.class);

	private int messageNumber;
	
	private String personal;
	
	private String from;
	
	private String subject;
	
	private Date sentDate;

	public static MailIntroduction fromMessage(Message message) throws MessagingException {
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
		return mailIntroduction;
	}
	
	/**
	 * @return the senderName
	 */
	public String getPersonal() {
		return personal;
	}

	/**
	 * @param senderName the senderName to set
	 */
	public void setPersonal(String senderName) {
		this.personal = senderName;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the title
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param title the title to set
	 */
	public void setSubject(String title) {
		this.subject = title;
	}

	/**
	 * @return the sendDate
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * @param sentDate the sendDate to set
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * @return the msgnum
	 */
	public int getMessageNumber() {
		return messageNumber;
	}

	/**
	 * @param msgnum the msgnum to set
	 */
	public void setMessageNumber(int msgnum) {
		this.messageNumber = msgnum;
	}
	
	
}
