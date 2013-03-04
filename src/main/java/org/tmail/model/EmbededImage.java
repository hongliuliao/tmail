/**
 * 
 */
package org.tmail.model;

/**
 * 邮件正文中的图片
 * @author hongliuliao
 *
 * createTime:2013-3-4 上午10:14:52
 */
public class EmbededImage {

	/**
	 * 文件描述,来自header
	 */
	private String contentDescription;
	
	/**
	 * 内容id,邮件正文中如果带图片可能会采用cid:contentId的形式存在
	 */
	private String contentId;

	
	/**
	 * @param contentDescription
	 * @param contentId
	 */
	public EmbededImage(String contentDescription, String contentId) {
		super();
		this.contentDescription = contentDescription;
		this.contentId = contentId.replace("<", "").replace(">", "");
	}

	/**
	 * @return the contentDescription
	 */
	public String getContentDescription() {
		return contentDescription;
	}

	/**
	 * @param contentDescription the contentDescription to set
	 */
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	
	
}
