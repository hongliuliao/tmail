/**
 * 
 */
package org.tmail.utils;

import javax.mail.Folder;

/**
 * @author hongliuliao
 *
 * createTime:2013-1-14 下午2:02:33
 */
public interface IReceiveHandler {

	public Object handler(Folder folder) throws Exception;
	
}
