/**
 * 
 */
package org.tmail.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmail.constants.TMailConstants;
import org.tmail.model.Account;
import org.tmail.model.MailIntroduction;
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
	@RequestMapping(value = "/mail/{msgnum}")
	public VCodeMsg getTMail(@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo,
			@PathVariable("msgnum") int msgnum) {
		Account account = Account.parseFromJson(accountInfo);
		if(account == null) {
			return VCodeMsg.failOf("account not found!");
		}
		TMail tmail = MailServiceImpl.getTMail(account, msgnum);
		if(tmail == null) {
			return VCodeMsg.failOf("mail not found which msgnum:" + msgnum);
		}
		return VCodeMsg.SUCCESS.setData(tmail);
	}
	
	@ResponseBody
	@RequestMapping(value = "/mail/list")
	public VCodeMsg getMailIntroductions(@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo) {
		Account account = Account.parseFromJson(accountInfo);
		List<MailIntroduction> introductions = this.MailServiceImpl.getMailIntroductions(account, 0, 10);
		return VCodeMsg.SUCCESS.setData(introductions);
	}
	
}
