package com.nani.boot1;

import com.nani.boot1.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//로그인 체크 인터셉터 거는 것. 이 함수 안에다 여러개쓰는 구조로 갈것
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //LoginCheckInterceptor 작성
    @Autowired
    LoginCheckInterceptor loginCheckInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(loginCheckInterceptor)
//                .excludePathPatterns("")
//            .addPathPatterns("");
    }
}
