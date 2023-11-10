package com.mysite.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// User 대신 SiteUser로 한 이유는 스프링 시큐리티에 이미 User 클래스가 있기 때문
// 사용자를 관리하는 SiteUser 엔티티
@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 번호증가
    private Long id;

    @Column(unique = true) // 중복 불가
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}
