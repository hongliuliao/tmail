/**
 * 
 */
package org.tmail.model;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-28 下午3:41:59
 */
@JsonIgnoreProperties({"inputStream"})
public class Attachment {

	private String name;
	
	private InputStream inputStream;

	private String contextType;
	
	/**
	 * @param name
	 * @param inputStream
	 * @param contextType
	 */
	public Attachment(String name, InputStream inputStream, String contextType) {
		super();
		this.name = name;
		this.inputStream = inputStream;
		this.contextType = contextType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public String getEncodeName() {
		try {
			return URLEncoder.encode(this.name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("unsupport utf-8");
		}
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the is
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param is the is to set
	 */
	public void setInputStream(InputStream is) {
		this.inputStream = is;
	}

	/**
	 * @return the contextType
	 */
	public String getContextType() {
		return contextType;
	}

	/**
	 * @param contextType the contextType to set
	 */
	public void setContextType(String contextType) {
		this.contextType = contextType;
	}
	
	
}
