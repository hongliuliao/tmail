/**
 * 
 */
package org.tmail.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
	
	private String htmlContext;
	
	private List<Attachment> attachments; 

	/**
	 * @return the context
	 */
	public String getContext() {
		if(StringUtils.isBlank(this.context)) {
			return this.getHtmlContext();
		}
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	public void addContext(String context) {
		if(this.context == null) {
			this.context = "";
		}
		this.context = this.context + context;
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

	/**
	 * @return the htmlContext
	 */
	public String getHtmlContext() {
		return htmlContext;
	}

	public void addHtmlContext(String htmlContext) {
		if(this.htmlContext == null) {
			this.htmlContext = "";
		}
		this.htmlContext = this.htmlContext + htmlContext;
	}
	
	/**
	 * @param htmlContext the htmlContext to set
	 */
	public void setHtmlContext(String htmlContext) {
		this.htmlContext = htmlContext;
	}

	/**
	 * @return the attachmentNames
	 */
	public List<String> getAttachmentNames() {
		if(CollectionUtils.isEmpty(attachments)) {
			return Collections.emptyList();
		}
		List<String> attachmentNames = new ArrayList<String>();
		for (Attachment attachment : this.attachments) {
			attachmentNames.add(attachment.getName());
		}
		return attachmentNames;
	}

	public void addAttachment(Attachment attachment) {
		if(this.attachments == null) {
			this.attachments = new ArrayList<Attachment>();
		} 
		this.attachments.add(attachment);
	}

	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
}
