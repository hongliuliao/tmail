/**
 * 
 */
package org.tmail.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.tmail.dao.MailDao;
import org.tmail.model.Account;
import org.tmail.model.Attachment;
import org.tmail.model.MailIntroduction;
import org.tmail.model.TMail;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-15 下午4:35:42
 */
@Service
public class MailServiceImpl {

	@Resource
	private MailDao mailDao;
	
	
	public TMail getTMail(Account account, int msgnum) {
		return mailDao.getTMail(account, msgnum);
	}
	
	public List<MailIntroduction> getMailIntroductions(Account account, int start, int count) {
		if(account == null) {
			return Collections.emptyList();
		}
		return this.mailDao.getMailIntroductions(account, start, count);
	}
	
	public Attachment getAttachment(Account account, int msgnum, String filename) {
		TMail tmail = this.getTMail(account, msgnum);
		if(CollectionUtils.isEmpty(tmail.getAttachments())) {
			return null;
		}
		for (Attachment attachment : tmail.getAttachments()) {
			if(attachment.getName().equals(filename)) {
				return attachment;
			}
		}
		return null;
	}
	
	public void sendMail(Account account, String toMail, String subject, String context) {
		this.mailDao.sendMail(account, toMail, subject, context);
	}
	
	public int getNewMailCount(Account account) {
		return this.mailDao.getNewMail(account, account.getLastMessageNum()).size();
	}
	
	public void removeMails(Account account, int[] msgnums) {
		this.mailDao.removeMails(account, msgnums);
	}
	
}
