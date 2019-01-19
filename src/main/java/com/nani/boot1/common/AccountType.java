package com.nani.boot1.common;

import java.util.Arrays;
import java.util.Optional;

public enum AccountType {
    EMAIL(1),
    KAKAO(2),
    GOOGLE(3),
    FACEBOOK(4);

    //java api찾아보자.
    public static Optional<AccountType> get(final int intValue){
        return Arrays.stream(values()).filter(at -> at.intValue() == intValue).findFirst();
    }

    private int value;

    AccountType(int value){
        this.value = value;
    }

    public int intValue(){
        return value;
    }
}
