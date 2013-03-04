/**
 * 
 */
package org.tmail.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author hongliuliao
 *
 * createTime:2013-3-4 上午11:56:54
 */
public class IOUtils {

	public static int copyAndCloseOutputStream(InputStream is, OutputStream os) {
		try {
			return org.apache.commons.io.IOUtils.copy(is, os);
		} catch (IOException e) {
			throw new RuntimeException("copy is error!", e);
		} finally {
			org.apache.commons.io.IOUtils.closeQuietly(os);
		}
	}
}
