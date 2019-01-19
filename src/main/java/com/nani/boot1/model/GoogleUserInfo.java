package com.nani.boot1.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleUserInfo {
        private String name;
        private String picture;
        private String email;
        @SerializedName("email_verified")
        private Boolean emailVerified;
}

//json 어떻게 날라온지는 공유private repository참고