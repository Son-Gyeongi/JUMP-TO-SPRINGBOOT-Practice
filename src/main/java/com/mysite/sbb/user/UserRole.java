package com.mysite.sbb.user;

import lombok.Getter;

/*
스프링 시큐리티는 인증 뿐만 아니라 권한도 관리한다.
인증후에 사용자에게 부여할 권한이 필요
 */
@Getter // 상수 자료형이므로 @Setter없이 @Getter만 사용가능하도록 했다.
public enum UserRole { // 열거 자료형(enum)
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
