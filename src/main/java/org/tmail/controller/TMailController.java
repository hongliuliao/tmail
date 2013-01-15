/**
 * 
 */
package org.tmail.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tmail.model.Account;
import org.tmail.model.TMail;
import org.tmail.service.MailServiceImpl;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-15 下午4:35:18
 */
public class TMailController {

	private MailServiceImpl mailService;
	
	@RequestMapping(value = "/mail/{accountId}/{msgnum}")
	public TMail getTMail(@PathVariable("accountId") long accountId, @PathVariable("msgnum") int msgnum) {
		Account account = mailService.getMailAccount(accountId);
		return mailService.getTMail(account, msgnum);
	}
	
}
