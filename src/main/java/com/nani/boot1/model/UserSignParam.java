package com.nani.boot1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSignParam {
    private int type;
    private String identification;
    private String credential = null;
}
