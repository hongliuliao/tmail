/**
 * 
 */
package org.tmail.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tmail.model.Account;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午2:42:15
 */
@Repository
public class AccountDao {

	private static final String ADD_ACCOUNT_INFO = "insert into tmail_accounts(email,email_password,user_id,last_message_num) values(?,?,?,?)";
	
	private static final String GET_ACCOUNT_BY_EMAIL = "select id,email,email_password, user_id,last_message_num from tmail_accounts where email=?";
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public void addAccount(Account account) {
		this.jdbcTemplate.update(ADD_ACCOUNT_INFO, account.getEmail(), account.getEmailPassword(), account.getUserId(), account.getLastMessageNum());
	}
	
	public Account getAccountByEmail(String userName) {
		List<Account> accounts = this.jdbcTemplate.query(GET_ACCOUNT_BY_EMAIL, new Object[] {userName}, new BeanPropertyRowMapper<Account>(Account.class));
		if(CollectionUtils.isNotEmpty(accounts)) {
			return accounts.get(0);
		}
		return null;
	}
	
	private Account getUniqAccount(List<Account> accounts) {
		if(CollectionUtils.isNotEmpty(accounts)) {
			return accounts.get(0);
		}
		return null;
	}
	
	public Account getAccountById(long id) {
		return getUniqAccount(this.jdbcTemplate.query("select id,email,email_password, user_id,last_message_num from tmail_accounts where id=?", new Object[] {id}, new BeanPropertyRowMapper<Account>(Account.class)));
	}
	
}
