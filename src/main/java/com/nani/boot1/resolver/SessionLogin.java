package com.nani.boot1.resolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//SessionLogin이라는 이름으로 어노테이션을 만드는거래.
//그래서 로그인하고 나서, 유저번호를 얻게하는것을 전부 이걸로 할거래.
public @interface SessionLogin {
	
}
