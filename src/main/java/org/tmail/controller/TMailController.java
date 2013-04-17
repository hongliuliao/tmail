/**
 * 
 */
package org.tmail.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tmail.constants.TMailConstants;
import org.tmail.model.Account;
import org.tmail.model.Attachment;
import org.tmail.model.MailIntroduction;
import org.tmail.model.MailListCodeMsg;
import org.tmail.model.Pagination;
import org.tmail.model.TMail;
import org.tmail.model.VCodeMsg;
import org.tmail.service.MailServiceImpl;
import org.tmail.utils.IOUtils;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-15 下午4:35:18
 */
@Controller
public class TMailController {

	@Resource
	private MailServiceImpl MailServiceImpl;
	
	private static Log log = LogFactory.getLog(TMailController.class);
	
	public static final int PAGE_SIZE = 10;
	
	@ResponseBody
	@RequestMapping(value = "/mail/{msgnum:\\d+}", method = RequestMethod.GET)
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
	public VCodeMsg getMailIntroductions(@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo, @RequestParam(value = "page", required = false) int page) {
		try {
			Account account = Account.parseFromJson(accountInfo);
			int start = (page - 1) * PAGE_SIZE;
			if(account == null) {
				VCodeMsg.failOf("Account is null! make sure your login info is correct!");
			}
			Pagination<MailIntroduction> pagination = this.MailServiceImpl.getMailIntroductions(account, start, PAGE_SIZE);
			MailListCodeMsg codeMsg = new MailListCodeMsg();
			if(pagination == null) {
				codeMsg.setCode(VCodeMsg.FAIL_CODE);
				codeMsg.setMsg("Can not found mail info!");
				return codeMsg;
			}
			codeMsg.setCode(VCodeMsg.SUCCESS_CODE);
			codeMsg.setData(pagination.getResultList());
			codeMsg.setStart(pagination.getStart());
			codeMsg.setCount(pagination.getCount());
			codeMsg.setTotalCount(pagination.getTotalCount());
			return codeMsg;
		} catch (Exception e) {
			log.error("get mail list error!", e);
			return VCodeMsg.failOf("get mail list error");
		}
	}
	
	@RequestMapping(value = "/mail/{msgnum}/attachment")
	public void getMailAttachment(@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo,
			@PathVariable("msgnum") int msgnum,
			@RequestParam("filename") String filename,
			HttpServletResponse response) throws IOException {
		Account account = Account.parseFromJson(accountInfo);
		filename = new String(filename.getBytes(Charsets.ISO_8859_1), Charsets.UTF_8);
		Attachment attachment = this.MailServiceImpl.getAttachment(account, msgnum, filename);
		if(attachment == null) {
			throw new RuntimeException("file not found!");
		}
		response.setContentType(attachment.getContextType());
		response.setHeader("Content-disposition", "attachment; filename=" + new String(attachment.getName().getBytes(Charsets.UTF_8), Charsets.ISO_8859_1));
		IOUtils.copyAndCloseStream(attachment.getInputStream(), response.getOutputStream());
	}
	
	@ResponseBody
	@RequestMapping(value = "/mail/", method = RequestMethod.POST)
	public VCodeMsg sendMail(@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo,
			@RequestParam("tomail") String toMail,
			@RequestParam("subject") String subject,
			@RequestParam("context") String context) {
		Account account = Account.parseFromJson(accountInfo);
		this.MailServiceImpl.sendMail(account, toMail, subject, context);
		return VCodeMsg.SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping(value = "/mail/new", method = RequestMethod.GET)
	public VCodeMsg newMail(@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo) {
		Account account = Account.parseFromJson(accountInfo);
		int newMailCount = this.MailServiceImpl.getNewMailCount(account);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("newMailCount", newMailCount);
		params.put("lastMessageNum", account.getLastMessageNum());
		return VCodeMsg.SUCCESS.setData(params);
	}
	
	@ResponseBody
	@RequestMapping(value = "/mail/remove", method = RequestMethod.POST)
	public VCodeMsg removeMail(@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo,
			@RequestParam("msgnums") int[] msgnums) {
		Account account = Account.parseFromJson(accountInfo);
		this.MailServiceImpl.removeMails(account, msgnums);
		return VCodeMsg.SUCCESS;
	}
	
}
