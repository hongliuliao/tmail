/**
 * 
 */
package org.tmail.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import org.tmail.constants.TMailConstants;
import org.tmail.model.VCodeMsg;
import org.tmail.utils.JacksonUtils;

/**
 * check user is logiin
 * @author hongliuliao
 *
 * createTime:2013-1-24 上午10:39:11
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Cookie cookie = WebUtils.getCookie(request, TMailConstants.LOGIN_ACCOUNT_COOKIE);
		if(cookie == null || StringUtils.isEmpty(cookie.getValue()) || "{}".equals(cookie.getValue())) {
			String errorJson = JacksonUtils.toJson(VCodeMsg.failOf("not login!"));
			response.addHeader("Content-Type", "application/json; charset=UTF-8");
			response.getWriter().write(errorJson);
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
