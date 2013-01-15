/**
 * 
 */
package org.tmail.service;

import org.tmail.dao.AccountDao;
import org.tmail.dao.MailReceiverDao;
import org.tmail.model.Account;
import org.tmail.model.TMail;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-15 下午4:35:42
 */
public class MailServiceImpl {

	private MailReceiverDao mailReceiverDao;
	
	private AccountDao accountDao;
	
	public TMail getTMail(Account account, int msgnum) {
		return mailReceiverDao.getTMail(account, msgnum);
	}
	
	public Account getMailAccount(long accountId) {
		return accountDao.getAccountById(accountId);
	}
}
