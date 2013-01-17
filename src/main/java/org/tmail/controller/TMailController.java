/**
 * 
 */
package org.tmail.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmail.model.Account;
import org.tmail.model.TMail;
import org.tmail.model.VCodeMsg;
import org.tmail.service.MailServiceImpl;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-15 下午4:35:18
 */
@Controller
public class TMailController {

	@Resource
	private MailServiceImpl MailServiceImpl;
	
	@ResponseBody
	@RequestMapping(value = "/mail/{accountId}/{msgnum}")
	public VCodeMsg getTMail(@PathVariable("accountId") long accountId, @PathVariable("msgnum") int msgnum) {
		Account account = MailServiceImpl.getMailAccount(accountId);
		if(account == null) {
			return VCodeMsg.failOf("account not found!");
		}
		TMail tmail = MailServiceImpl.getTMail(account, msgnum);
		if(tmail == null) {
			return VCodeMsg.failOf("mail not found which msgnum:" + msgnum);
		}
		return VCodeMsg.SUCCESS.setData(tmail);
	}
	
	
	
}
