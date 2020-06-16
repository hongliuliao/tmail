/**
 * 
 */
package org.tmail.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.tmail.model.Account;
import org.tmail.model.TMail;


/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午7:01:42
 */
public class MailDaoTest extends BaseTest {

	MailDao mailDao = new MailDao();
	
	@Before
	public void init() {
//		receiver.setPop3Host("pop.sohu.com");
//		receiver.setUserName("testliao4@sohu.com");
//		receiver.setPassword("123456");
//		receiver.setPop3Host("pop.163.com");
//		receiver.setUserName("testliao4@163.com");
//		receiver.setPassword("123456");
	}
	
	/**
	 * Test method for {@link org.tmail.dao.MailDao#getMail()}.
	 */
	@Test
	public void testGetRecentMailIntroductions() {
		Account account = Account.parseFromJson("{'email':'testliao3@163.com','password':'123456'}");
		System.out.println(mailDao.getMailIntroductions(account, 0, 10));
	}

	@Test
	public void testCountNewMail() {
		Account account = Account.parseFromJson("{'email':'testliao3@163.com','password':'123456'}");
		System.out.println(this.mailDao.countNewMail(account, 4));
	}
	
	@Test
	public void testGetTMail() {
		Account account = Account.parseFromJson("{'email':'testliao3@163.com','password':'123456'}");
		TMail mail = this.mailDao.getTMail(account, 101);
		System.out.println(mail);
	}
	
	@Ignore
	public void testSendMail() {
		Account account = Account.parseFromJson("{'email':'testliao3@163.com','password':'123456'}");
		this.mailDao.sendMail(account, "357857613@qq.com", "test", "test2");
	}
	
}
