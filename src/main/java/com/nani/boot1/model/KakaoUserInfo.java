package com.nani.boot1.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserInfo {
        private long id;
        private String kaccount_email;
        private boolean kaccount_email_verified;
        private KakaoProperties properties;

        @Data
        public class KakaoProperties{
                private String nickname;
        }
}

