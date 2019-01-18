package com.nani.boot1.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nani.boot1.common.Constant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.nani.study3.service.SignService;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		HttpSession session = request.getSession();

		Integer userIdx = (Integer)session.getAttribute(Constant.SESSION_KEY_LOGIN_USER_IDX);
//		Integer userIdx = (Integer)session.getAttribute(SignService.SIGN_IN_USER_IDX_KEY);
		
		if(userIdx == null) {
		
			try {
				response.sendRedirect("/site/signin.html");
			}	catch(Exception e) {
				e.printStackTrace();
			}
			
//			response.setCharacterEncoding("UTF-8");
////			response.addHeader("Content-Type", "application/json;charset=UTF-8");
//			try{
//				PrintWriter printWriter = response.getWriter();
//				printWriter.print("NEED LOGIN");
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			//이렇게 하면, 마냥 기다리다 request timeout까지 기다리다가 끝나버림.
			return false;
		}	
		
		//다음으로 가게 해줄려고 할 
//		return false;
		//return false;는 다음으로 못가게 한다는 소리;
		return super.preHandle(request, response, handler);
	}

	/*
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	*/
}

