/**
 * 
 */
package org.tmail.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tmail.dao.AccountDao;
import org.tmail.dao.MailReceiverDao;
import org.tmail.model.Account;
import org.tmail.model.TMail;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-15 下午4:35:42
 */
@Service
public class MailServiceImpl {

	@Resource
	private MailReceiverDao mailReceiverDao;
	
	@Resource
	private AccountDao accountDao;
	
	public TMail getTMail(Account account, int msgnum) {
		return mailReceiverDao.getTMail(account, msgnum);
	}
	
	public Account getMailAccount(long accountId) {
		return accountDao.getAccountById(accountId);
	}
}
