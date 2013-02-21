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

	NETEASE("@163.com", "pop3.163.com", "smtp.163.com"),
	QQ("@qq.com", "pop.qq.com", "smtp.qq.com"),
	SOHU_INC("@sohu-inc.com", "mail.sohu-inc.com", "mail.sohu-inc.com"),
	GMAIL("@gmail.com", "pop.gmail.com", "smtp.gmail.com");
	
	private final String mailSuffix;
	
	private final String pop3Host;
	
	private final String smtpHost;
	/**
	 * @param pop3Host
	 * @param smtpHost
	 */
	private MailHost(String mailSuffix, String pop3Host, String smtpHost) {
		this.pop3Host = pop3Host;
		this.smtpHost = smtpHost;
		this.mailSuffix = mailSuffix;
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
		MailHost[] hosts = values();
		for (MailHost mailHost : hosts) {
			if(mail.endsWith(mailHost.getMailSuffix())) {
				return mailHost;
			}
		}
		return null;
	}
	/**
	 * @return the mailRegular
	 */
	public String getMailSuffix() {
		return mailSuffix;
	}
	
}
