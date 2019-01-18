package com.nani.boot1.service;

import com.nani.boot1.common.Constant;
import com.nani.boot1.common.ErrorCode;
import com.nani.boot1.dao.UserAuthDAO;
import com.nani.boot1.dao.UserDAO;
import com.nani.boot1.exception.BadRequestException;
import com.nani.boot1.model.KakaoResultJson;
import com.nani.boot1.model.User;
import com.nani.boot1.model.UserAuth;
import com.nani.boot1.rest.KakaoAPI;
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
    KakaoAPI kakaoAPI;

    @Transactional
    @Override
    public User in(UserAuth userAuth) {
        switch ( userAuth.getType() ) {
            case Constant.ACCOUNT_TYPE_EMAIL:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);

            case Constant.ACCOUNT_TYPE_GOOGLE:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);

            case Constant.ACCOUNT_TYPE_KAKAO:
                UserAuth auth = userAuthDAO.selectUserAuth(userAuth);

                if (auth != null)
                    return auth.getUser(); //user 던지고 끝

                return up(userAuth);

//                if(auth == null)
//                    throw new BadRequestException(0, "not found user");

            case Constant.ACCOUNT_TYPE_FACEBOOK: //과제
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
                //                break;
            default:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
        }
    }

    @Transactional //Transactional은 autowired관계에서만 동작한다.
    @Override
    public User up(UserAuth userAuth) {

        switch ( userAuth.getType() ){
            case Constant.ACCOUNT_TYPE_EMAIL:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
//                break;
            case Constant.ACCOUNT_TYPE_GOOGLE:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
//                break;
            case Constant.ACCOUNT_TYPE_KAKAO:
                System.out.println("hihihihihihihihi?");
                String accessToken = userAuth.getCredential();
                KakaoResultJson kakaoResultJson = getKakaoUser(accessToken);
                if( kakaoResultJson == null) {
                    throw new BadRequestException(0, "kakao fail"); //안만들어놨기 때문에, 일단 급하게 이렇게 했고, 나중에 관리해줘라.
                }
                String kakaoId = ""+kakaoResultJson.getId();
                String kakaoNickname =  kakaoResultJson.getProperties().getNickname();

                UserAuth auth = new UserAuth();
                auth.setType(Constant.ACCOUNT_TYPE_KAKAO);
                auth.setIdentification(kakaoId);

                if(userAuthDAO.selectUserAuth(auth) != null)
                    throw new BadRequestException(0, "already exists");


//                String kakaoEmail = kakaoResultJson.getKaccount_email();
//                String kakaoEmailId = kakaoEmail.substring(0, kakaoEmail.indexOf("@"));

                User user = new User();
                user.setName(kakaoNickname);
//                user.setName(kakaoEmailId);
                userDAO.insertUser(user);

                auth.setUserIdx(user.getIdx());

                userAuthDAO.insertUserAuth(auth);

                return user;
//                break;
            case Constant.ACCOUNT_TYPE_FACEBOOK: //과제
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
//                break;
            default:
                throw new BadRequestException(ErrorCode.INVALID_PARAM_ACCOUNT_TYPE);
//                break;
        }

//        return null;
    }

    private KakaoResultJson getKakaoUser(String accessToken) throws RuntimeException {
        try{

            KakaoResultJson kakaoResultJson = kakaoAPI.userMeForToken("Bearer " + accessToken).execute().body();
            //sendApi(access_token);

            //이메일 체크하는 부분.
//            if(kakaoResultJson.getKaccount_email() == null || !kakaoResultJson.isKaccount_email_verified())
//                return null;

//            KakaoResultJson kakaoResultJson2 = checkIdByAdminKey(kakaoResultJson.id);
            //밑의 코드가 위의 코드를 대체
            KakaoResultJson kakaoResultJson2 = kakaoAPI.userMeForUserId("user_id", ""+kakaoResultJson.getId()).execute().body();

            if(kakaoResultJson2.getId() != kakaoResultJson.getId()) {
                System.out.println("kakao id not equal");
                return null;
            }

            return kakaoResultJson2;

        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException();
    }
}
