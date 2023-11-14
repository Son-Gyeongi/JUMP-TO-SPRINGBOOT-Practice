package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

// 엔티티는 데이터베이스 테이블과 매핑되는 자바 클래스
@Getter
@Setter
@Entity // JPA가 엔티티로 인식
public class Question {

    /*
    @GeneratedValue 애너테이션을 적용하면 데이터를 저장할 때
    해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하여 저장

    strategy는 고유번호를 생성하는 옵션으로
    GenerationType.IDENTITY는 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용
     */
    @Id // id 속성을 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 질문 고유 번호

    @Column(length = 200) // length는 컬럼의 길이를 설정
    private String subject; // 질문 제목

    // "TEXT"은 "내용"처럼 글자 수를 제한할 수 없는 경우에 사용
    @Column(columnDefinition = "TEXT") // columnDefinition은 컬럼의 속성을 정의
    private String content; // 질문 내용

    private LocalDateTime createDate; // 질문을 작성한 일시

    // 1:N관계 질문 하나에 답변은 여러개
    // mappedBy - 참조 엔티티의 속성명, Answer 엔티티에서 Question 엔티티를 참조한 속성명 question을 mappedBy에 전달해야 한다.
    // CascadeType.REMOVE - 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne // 여러개의 질문이 한 명의 사용자에게 작성
    private SiteUser author; // 글쓴이

    private LocalDateTime modifyDate; // 수정 일시
}
