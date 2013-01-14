/**
 * 
 */
package org.tmail.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午4:31:57
 */
public abstract class BaseDao {

	@Resource
	protected JdbcTemplate jdbcTemplate;
}
