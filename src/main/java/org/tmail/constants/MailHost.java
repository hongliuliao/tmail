/**
 * 
 */
package org.tmail.constants;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-29 下午1:28:13
 */
public enum MailHost {

	NETEASE("pop3.163.com", "smtp.163.com"),
	QQ("pop.qq.com", "smtp.qq.com"),
	SOHU_INC("mail.sohu-inc.com", "mail.sohu-inc.com");
	
	private final String pop3Host;
	
	private final String smtpHost;
	/**
	 * @param pop3Host
	 * @param smtpHost
	 */
	private MailHost(String pop3Host, String smtpHost) {
		this.pop3Host = pop3Host;
		this.smtpHost = smtpHost;
	}
	/**
	 * @return the pop3Host
	 */
	public String getPop3Host() {
		return pop3Host;
	}
	/**
	 * @return the smtpHost
	 */
	public String getSmtpHost() {
		return smtpHost;
	}
	
	public static MailHost getMailHostFromMail(String mail) {
		if(mail.endsWith("@163.com")) {
			return NETEASE;
		}
		if(mail.endsWith("@qq.com")) {
			return QQ;
		}
		if(mail.endsWith("@sohu-inc.com")) {
			return SOHU_INC;
		}
		return null;
	}
	
}
