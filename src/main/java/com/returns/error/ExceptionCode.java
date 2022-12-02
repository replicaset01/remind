package com.returns.error;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUNT(404, "Member Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
