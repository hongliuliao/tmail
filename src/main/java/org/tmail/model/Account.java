/**
 * 
 */
package org.tmail.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.tmail.utils.JacksonUtils;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午2:03:33
 */
public class Account {

	private int id;
	
	private String email;
	private String password;
	
	private int userId;
	
	private int lastMessageNum;
	
	/**
	 * 
	 */
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getPop3Host() {
		if(this.email.endsWith("@163.com")) {
			return "pop3.163.com";
		}
		if(this.email.endsWith("@qq.com")) {
			return "pop.qq.com";
		}
		if(this.email.endsWith("@sohu-inc.com")) {
			return "mail.sohu-inc.com";
		}
		throw new IllegalArgumentException("Unsupport email:" + this.email);
	}
	
	public static Account parseFromJson(String accountInfo) {
		return JacksonUtils.toBean(accountInfo, Account.class);
	}
	
}
