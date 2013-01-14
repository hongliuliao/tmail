/**
 * 
 */
package org.tmail.model;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午6:47:43
 */
public class TextMail {

	private int mailId;
	
	private String title;
	
	private String context;

	/**
	 * @return the mailId
	 */
	public int getMailId() {
		return mailId;
	}

	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}
	
	
}
