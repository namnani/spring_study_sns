package com.nani.boot1.service;

import com.nani.boot1.model.User;
import com.nani.boot1.model.UserAuth;
import com.nani.boot1.model.UserSignParam;

public interface SignService {
    User in(UserSignParam param);
    User up(UserSignParam param);
}
