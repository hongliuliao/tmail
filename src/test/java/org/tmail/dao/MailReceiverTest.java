/**
 * 
 */
package org.tmail.dao;

import org.junit.Before;
import org.junit.Test;
import org.tmail.model.Account;
import org.tmail.model.TMail;


/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午7:01:42
 */
public class MailReceiverTest {

	MailReceiverDao receiver = new MailReceiverDao();
	
	private AccountDao accountDao = new AccountDao();
	
	@Before
	public void init() {
//		receiver.setPop3Host("pop.sohu.com");
//		receiver.setUserName("testliao4@sohu.com");
//		receiver.setPassword("123456a");
//		receiver.setPop3Host("pop.163.com");
//		receiver.setUserName("testliao4@163.com");
//		receiver.setPassword("123456a");
	}
	
	/**
	 * Test method for {@link org.tmail.dao.MailReceiverDao#getMail()}.
	 */
	@Test
	public void testGetRecentMailIntroductions() {
		Account account = this.accountDao.getAccountById(1L);
		System.out.println(receiver.getRecentMailIntroductions(account, 10));
	}

	@Test
	public void testCountNewMail() {
		Account account = this.accountDao.getAccountById(1L);
		System.out.println(this.receiver.countNewMail(account, 4));
	}
	
	@Test
	public void testGetTMail() {
		Account account = accountDao.getAccountByEmail("testliao4@163.com");
		TMail mail = this.receiver.getTMail(account, 1);
		System.out.println(mail);
	}
	
}
