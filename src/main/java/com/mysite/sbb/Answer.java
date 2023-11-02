package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 엔티티는 데이터베이스 테이블과 매핑되는 자바 클래스
@Getter
@Setter
@Entity // JPA가 엔티티로 인식
public class Answer {
    @Id // id 속성을 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 답변 고유 번호

    @Column(columnDefinition = "TEXT") // columnDefinition은 컬럼의 속성을 정의
    private String content; // 답변 내용

    private LocalDateTime createDate; // 답변을 작성한 일시

    @ManyToOne // 답변은 많고 질문은 하나이다. N:1의 관계
    private Question question; // 질문
}
