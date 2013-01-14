/**
 * 
 */
package org.tmail.dao;

import org.junit.Before;
import org.junit.Test;
import org.tmail.dao.MailReceiver;


/**
 * @author hongliuliao
 *
 * createTime:2013-1-10 下午7:01:42
 */
public class MailReceiverTest {

	MailReceiver receiver = new MailReceiver("testliao4@163.com", "123456a", "pop.163.com");
	
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
	 * Test method for {@link org.tmail.dao.MailReceiver#getMail()}.
	 */
	@Test
	public void testGetRecentMailIntroductions() {
		System.out.println(receiver.getRecentMailIntroductions(10));
	}

	@Test
	public void testCountNewMail() {
		System.out.println(this.receiver.countNewMail(4));
	}
	
}
