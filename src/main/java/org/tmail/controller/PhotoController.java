/**
 * 
 */
package org.tmail.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.Charsets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tmail.constants.TMailConstants;
import org.tmail.utils.IOUtils;

/**
 * @author hongliuliao
 *
 * createTime:2013-3-4 上午10:50:05
 */
@Controller
public class PhotoController {

	private static final Log log = LogFactory.getLog(PhotoController.class);
	
	@RequestMapping(value = "/image/{mailId}/{imageName}", method = RequestMethod.GET)
	public void getImage(
			@CookieValue(TMailConstants.LOGIN_ACCOUNT_COOKIE) String accountInfo,
			@PathVariable("mailId") int mailId,
			@PathVariable("imageName") String encodeImageName, 
			HttpServletResponse response) throws Exception {
		String imageName = new String(Base64.decodeBase64(encodeImageName), Charsets.UTF_8);
		File file = new File(TMailConstants.EMBEDDED_IMAGE_STORE_DIR + mailId + "/" + imageName);
		if(!file.exists()) {
			response.setStatus(404);
			log.warn("Image not found! which path:" + file.getPath());
			return;
		}
		IOUtils.copyAndCloseStream(new FileInputStream(file), response.getOutputStream());
	}
}
