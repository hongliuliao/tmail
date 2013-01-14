/**
 * 
 */
package org.tmail.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午2:03:33
 */
public class Account {

	private int id;
	
	private String email;
	private String emailPassword;
	
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
		this.emailPassword = password;
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
	public String getEmailPassword() {
		return emailPassword;
	}
	/**
	 * @param password the password to set
	 */
	public void setEmailPassword(String password) {
		this.emailPassword = password;
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
	
}
