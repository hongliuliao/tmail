/**
 * 
 */
package org.tmail.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author hongliuliao
 *
 * createTime:2013-3-4 上午10:28:46
 */
public class DownloadUtils {

	public static void downloadFile(String dir, String name, InputStream is) {
		File downloadDir = new File(dir);
		if(!downloadDir.exists()) {
			new File(dir).mkdirs();
		}
		if(!downloadDir.isDirectory()) {
			throw new RuntimeException("dir is not exist! dir:" + dir);
		}
		try {
			FileOutputStream fos = new FileOutputStream(dir + "/" + name);
			IOUtils.copy(is, fos);
		} catch (Exception e) {
			throw new RuntimeException("download file error!", e);
		}
	}
}
