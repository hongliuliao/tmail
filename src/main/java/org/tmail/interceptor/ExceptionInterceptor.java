/**
 * $Id: ExceptionHandler.java 18598 2012-03-09 03:26:58Z taochen $
 * (C)2011 Sohu Inc.
 */
package org.tmail.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: taochen
 * @sine: 12-3-8 下午2:35
 * 一个异常拦截器 内部逻辑自己实现 也可以不用
 */
@Component
public class ExceptionInterceptor implements HandlerExceptionResolver, PriorityOrdered {

	private final Log log = LogFactory.getLog(ExceptionInterceptor.class);

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		log.error("request uri:" + request.getRequestURI() + 
				" submit method:" + request.getMethod() +
				" parameter:" + request.getParameterMap(), ex);
		return null;
	}

}
