package com.nani.boot1.controller;

import com.google.gson.internal.LinkedTreeMap;
import com.nani.boot1.common.Constant;
import com.nani.boot1.model.User;
import com.nani.boot1.model.UserAuth;
import com.nani.boot1.service.SignService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(Constant.REST_API_VERSION+"/sign")
public class SignController {


    @Autowired
    SignService signService;

    //이메일 패스워드 로그인
    //소셜 로그인 가입/로그인
    @PostMapping("/in")
    public Object inPost(
            HttpSession session,
            @RequestParam int type,
            @RequestParam String identification,
            @RequestParam(required = false) String credential
    ){
        UserAuth userAuth = new UserAuth();
        userAuth.setType(type);
        userAuth.setIdentification(identification);
        userAuth.setCredential(credential);

        User user = signService.in(userAuth);
        session.setAttribute(Constant.SESSION_KEY_LOGIN_USER_IDX, user.getIdx());

        Map<String, Object> map = new LinkedTreeMap<>();
        map.put("succ", true);
        map.put("msg", "SUCC");
        return map;
    }


    //이메일 패스워드 가입.
    @PostMapping("/up")
    public Object upPost(
            int type,
            String identification,
            String credential
    ) {
        UserAuth userAuth = new UserAuth();
        userAuth.setType(type);
        userAuth.setIdentification(identification);
        userAuth.setCredential(credential);

//        User user = signService.in(userAuth);
        User user = signService.up(userAuth);

        //맵이 너무 싫어서 쓰는것
        //그런데 이것을 쓰면 성능이슈가 꽤 안좋다고 한다.
        /*
        return new HashMap<String, Object>() {{
            put("succ", true);
            put("msg", "SUCC");
        }};
        */

        Map<String, Object> map = new LinkedTreeMap<>();
        map.put("succ", true);
        map.put("msg", "SUCC nani good");
        return map;
    }

    @RequestMapping(value = "/out", method = {RequestMethod.GET, RequestMethod.POST})
    public Object out(HttpSession session){
        session.invalidate();

        Map<String, Object> map = new LinkedTreeMap<>();
        map.put("succ", true);

        map.put("msg", "SUCC");

        return map;
    }
}
