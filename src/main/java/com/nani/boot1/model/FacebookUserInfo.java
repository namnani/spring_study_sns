package com.nani.boot1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacebookUserInfo {
    private String id;
    private String name;
    private String email;
}
