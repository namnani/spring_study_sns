package com.nani.boot1;

import com.google.gson.Gson;
import com.nani.boot1.model.GoogleUserInfo;
import com.nani.boot1.rest.GoogleOAuthAPI;
import com.nani.boot1.rest.GoogleUserInfoAPI;
import com.nani.boot1.rest.KakaoUserinfoAPI;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {


    //지금은 카카오 하나인데, 구글이 추가된다하면 이제 구글이 하나 추가되고, 인터페이스가 하나 추가되고, 서비스가서 코드짜는게 끝임.
    //이런식으로 restAPI쓴다하면, 누적될수록 효율이 높아진다.
    //밑의 레트로핏 코드는 안바뀌고, 계속 위에만 추가될 뿐이니까.
    //이 사용성을 보더라도, 카카오가 기존에 쓰는 기존의 코드가 너무 부적절하다.

    @Bean
    public KakaoUserinfoAPI getKakaoAPI() {
        return getRetrofit(KakaoUserinfoAPI.BASE_URL).create(KakaoUserinfoAPI.class);
    }

    @Bean
    public GoogleOAuthAPI getGoogleOAuthAPI(){
        return getRetrofit(GoogleOAuthAPI.BASE_URL).create(GoogleOAuthAPI.class);
    }

    @Bean
    public GoogleUserInfoAPI getGoogleUserInfoAPI(){
        return getRetrofit(GoogleUserInfoAPI.BASE_URL).create(GoogleUserInfoAPI.class);
    }

    private Retrofit getRetrofit(String BASE_URL) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();
        return retrofit;
    }
}
