/**
 * 
 */
package org.tmail.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午6:47:43
 */
public class TMail {

	private MailIntroduction mailIntroduction;
	
	private String context;

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

	/**
	 * @return the mailIntroduction
	 */
	public MailIntroduction getMailIntroduction() {
		return mailIntroduction;
	}

	/**
	 * @param mailIntroduction the mailIntroduction to set
	 */
	public void setMailIntroduction(MailIntroduction mailIntroduction) {
		this.mailIntroduction = mailIntroduction;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
