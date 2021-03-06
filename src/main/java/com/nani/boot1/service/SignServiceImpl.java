package com.nani.boot1.service;

import com.nani.boot1.common.AccountType;
import com.nani.boot1.common.ErrorCode;
import com.nani.boot1.dao.UserAuthDAO;
import com.nani.boot1.dao.UserDAO;
import com.nani.boot1.exception.BadRequestException;
import com.nani.boot1.model.*;
import com.nani.boot1.rest.KakaoUserinfoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignServiceImpl implements SignService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserAuthDAO userAuthDAO;

    @Autowired
    KakaoUserinfoAPI kakaoUserinfoAPI;

    @Transactional
    @Override
    public User in(UserSignParam param) {
        AccountType accountType;
        try{
            accountType = AccountType.get(param.getType()).get();
        } catch(Exception e){
            throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
        }

        switch ( accountType) {
            case EMAIL:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
            case GOOGLE:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
            case KAKAO:
                UserAuth auth = userAuthDAO.selectUserAuth(param);

                if(auth != null)
                    return auth.getUser();

                return up(param);

            case FACEBOOK: //과제
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
        }

        throw new BadRequestException(ErrorCode.UNKNOWN);
    }

    @Transactional //Transactional은 autowired관계에서만 동작한다.
    @Override
    public User up(UserSignParam param) {
        AccountType accountType;

        try{
            accountType = AccountType.get(param.getType()).get();
        } catch (Exception e){
            throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
        }

        switch(accountType){
            case EMAIL:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
            case GOOGLE:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
            case KAKAO:
                String accessToken = param.getIdentification();
                KakaoUserInfo kakaoResultJson = getKakaoUserInfo(accessToken);
                if(kakaoResultJson == null)
                    throw new BadRequestException(0, "kakao fail");

                String kakaoId = "" + kakaoResultJson.getId();

                UserSignParam queryParam = new UserSignParam();
                queryParam.setType(AccountType.KAKAO.intValue());
                queryParam.setIdentification(kakaoId);

                if(userAuthDAO.selectUserAuth(queryParam) != null)
                    throw new BadRequestException(0, "already exists");

                String kakaoNickname = kakaoResultJson.getProperties().getNickname();

                User user = new User();
                user.setName(kakaoNickname);
                userDAO.insertUser(user);

                UserAuth auth = new UserAuth();
                auth.setType(AccountType.KAKAO.intValue());
                auth.setUserIdx(user.getIdx());

                userAuthDAO.insertUserAuth(auth);

                return user;
            case FACEBOOK:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
        }
    }

    private GoogleUserInfo getGoogleUserInfo(String accessToken) {
        return null;
    }

    private KakaoUserInfo getKakaoUserInfo(String accessToken){
        try{
            KakaoUserInfo kakaoResultJson = kakaoUserinfoAPI.userMeForToken("Bearer " + accessToken).execute().body();

            KakaoUserInfo kakaoResultJson2 = kakaoUserinfoAPI.userMeForUserId("user_id", "" + kakaoResultJson.getId()).execute().body();

            if(kakaoResultJson2.getId() != kakaoResultJson.getId()){
                System.out.println("kakao id not equal");
                return null;
            }
            return kakaoResultJson2;
        } catch(Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    private FacebookUserInfo getFacebookUserInfo(String accessToken){
        return null;
    }
}
