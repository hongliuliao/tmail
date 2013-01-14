/**
 * 
 */
package org.tmail.dao;

import org.tmail.model.User;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午4:30:46
 */
public class UserDao extends BaseDao {

	
	public void addUser(User user) {
		this.jdbcTemplate.update("insert into tmail_users(name,password) values(?,?)", user.getName(), user.getPassword());
	}
	

}
