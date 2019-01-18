package com.nani.boot1.rest;

import com.nani.boot1.common.Constant;
import com.nani.boot1.model.KakaoResultJson;
import retrofit2.Call;
import retrofit2.http.*;

public interface KakaoAPI {
    String BASE_URL = "https://kapi.kakao.com";

    ///이나 , 안붙이는거나 차이가 없네.
    @GET("v1/user/me")
    Call<KakaoResultJson> userMeForToken(@Header("Authorization") String authorization);

    @POST("v1/user/me")
    @Headers("Authorization: KakaoAK " + Constant.KAKAO_ADMIN_KEY)
    @FormUrlEncoded //레트로핏쓸때는 꼭해줘야한다네
    Call<KakaoResultJson> userMeForUserId(
            @Field("target_id_type") String targetIdType,
            @Field("target_id") String targetId);

}
