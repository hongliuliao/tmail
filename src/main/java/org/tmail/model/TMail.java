/**
 * 
 */
package org.tmail.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.Charsets;
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
	
	private List<EmbededImage> embededImages;

	/**
	 * @return the context
	 */
	public String getContext() {
		String htmlContext = this.getHtmlContext();
		if(StringUtils.isNotBlank(htmlContext)) {
			return htmlContext;
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
		if(CollectionUtils.isNotEmpty(this.embededImages) && htmlContext != null) {
			for (EmbededImage image : embededImages) {
				String imageName = Base64.encodeBase64String(image.getContentDescription().getBytes(Charsets.UTF_8));
				htmlContext = htmlContext.replace("cid:" + image.getContentId(), "image/" + this.mailIntroduction.getMessageNumber() + "/" + imageName);
			}
		}
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
	
	public void addEmbededImage(EmbededImage embededImage) {
		if(this.embededImages == null) {
			this.embededImages = new ArrayList<EmbededImage>();
		}
		this.embededImages.add(embededImage);
	}

	/**
	 * @return the embededImages
	 */
	public List<EmbededImage> getEmbededImages() {
		return embededImages;
	}

	/**
	 * @param embededImages the embededImages to set
	 */
	public void setEmbededImages(List<EmbededImage> embededImages) {
		this.embededImages = embededImages;
	}
	
	
	
}
