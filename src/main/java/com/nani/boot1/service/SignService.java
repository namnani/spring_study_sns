package com.nani.boot1.service;

import com.nani.boot1.model.User;
import com.nani.boot1.model.UserAuth;

public interface SignService {
    User in(UserAuth userAuth);
    User up(UserAuth userAuth);
}
