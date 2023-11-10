package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 스프링 시큐리티의 설정
@Configuration //  스프링의 환경설정 파일임을 의미하는 애너테이션
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
public class SecurityConfig {
    // 스프링 시큐리티의 세부 설정은 SecurityFilterChain 빈을 생성하여 설정
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // 모든 인증되지 않은 요청을 허락한다는 의미
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))) // CSRF 처리시 H2 콘솔은 예외로 처리 가능
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))); // H2 콘솔의 화면 해결

        return http.build();
    }

    /*
    PasswordEncoder 빈(bean)으로 등록해서 사용
    암호화 방식을 변경하면 BCryptPasswordEncoder를 사용한 모든 프로그램을 일일이 찾아서 수정해야 하기 때문
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 암호화
        return new BCryptPasswordEncoder();
    }
}
