/**
 * 
 */
package org.tmail.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.tmail.model.Account;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午3:43:32
 */
public class AccountDaoTest extends BaseTest {

	@Resource
	private AccountDao userDao;
	
	/**
	 * Test method for {@link org.tmail.dao.AccountDao#addAccount(org.tmail.model.Account)}.
	 */
	@Test
	public void testAddAccount() {
		Account account = new Account("testliao4@163.com", "123456a");
		account.setLastMessageNum(1);
		account.setUserId(1);
		this.userDao.addAccount(account);
	}
	
	@Test
	public void testGetAccountByEmail() {
		Account user = this.userDao.getAccountByEmail("testliao4@163.com");
		System.out.println(user);
	}

}
