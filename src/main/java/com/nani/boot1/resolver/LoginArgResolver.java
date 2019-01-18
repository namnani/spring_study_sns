package com.nani.boot1.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nani.boot1.common.Constant;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

//import com.nani.study3.service.SignService;

public class LoginArgResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		// TODO Auto-generated method stub
		return arg0.getParameterAnnotation(SessionLogin.class) != null;
	}

	@Override
	public Object resolveArgument(
			MethodParameter arg0,
			ModelAndViewContainer arg1,
			NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) arg2.getNativeRequest();
		HttpSession session = request.getSession();
		Integer userIdx = (Integer) session.getAttribute(Constant.SESSION_KEY_LOGIN_USER_IDX);
		return userIdx;
	}

}
