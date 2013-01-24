/**
 * 
 */
package org.tmail.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import org.tmail.constants.TMailConstants;

/**
 * check user is logiin
 * @author hongliuliao
 *
 * createTime:2013-1-24 上午10:39:11
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Cookie cookie = WebUtils.getCookie(request, TMailConstants.LOGIN_ACCOUNT_COOKIE);
		if(cookie == null) {
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
