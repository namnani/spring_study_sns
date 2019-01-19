package com.nani.boot1.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleOAuth {
    @SerializedName("access_token")
    private String accessToken;
}
