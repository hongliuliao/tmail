/**
 * 
 */
package org.tmail.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.tmail.constants.MailHost;
import org.tmail.utils.JacksonUtils;

/**
 * 用户信息类
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午2:03:33
 */
public class Account {

	/**
	 * 邮箱账号
	 */
	private String email;
	/**
	 * 邮箱密码
	 */
	private String password;
	
	/**
	 * 最后一次读取到的邮件编号
	 */
	private int lastMessageNum = 1;
	
	public Account() {
	}
	
	/**
	 * @param email
	 * @param password
	 */
	public Account(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	/**
	 * @return the userName
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setEmail(String userName) {
		this.email = userName;
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
	 * @return the lastMessageNum
	 */
	public int getLastMessageNum() {
		return lastMessageNum;
	}
	/**
	 * @param lastMessageNum the lastMessageNum to set
	 */
	public void setLastMessageNum(int lastMessageNum) {
		this.lastMessageNum = lastMessageNum;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getPop3Host() {
		MailHost mailHost = MailHost.getMailHostFromMail(this.email);
		if(mailHost == null) {
			throw new IllegalArgumentException("Unsupport email:" + this.email);
		}
		return mailHost.getPop3Host();
	}
	
	public String getSmtpHost() {
		MailHost mailHost = MailHost.getMailHostFromMail(this.email);
		if(mailHost == null) {
			throw new IllegalArgumentException("Unsupport email:" + this.email);
		}
		return mailHost.getSmtpHost();
	}
	
	public static Account parseFromJson(String accountInfo) {
		return JacksonUtils.toBean(accountInfo, Account.class);
	}
	
}
