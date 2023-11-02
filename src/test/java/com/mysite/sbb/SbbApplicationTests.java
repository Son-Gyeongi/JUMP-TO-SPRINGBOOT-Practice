package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * JUnit은 테스트코드를 작성하고 작성한 테스트코드를
 * 실행하기 위해 사용하는 자바의 테스트 프레임워크
 */
@SpringBootTest // 스프링부트 테스트 클래스임을 의미
class SbbApplicationTests {

    /*
    리포지터리 테스트
    DI(Dependency Injection) - 스프링이 객체를 대신 생성하여 주입한다.
     */
    @Autowired // 스프링의 DI 기능으로 questionRepository 객체를 스프링이 자동으로 생성
    private QuestionRepository questionRepository;

    @Test // 테스트 메서드
    void testJpa() {
        /*
        // Save, 데이터 저장
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1); // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2); // 두번째 질문 저장
         */
        /*
        // findAll, 모든 데이터 조회
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
         */
        /*
        // findById, Id값으로 데이터 조회
        // Optional은 null 처리를 유연하게 처리하기 위해 사용하는 클래스
        Optional<Question> oq = this.questionRepository.findById(1);

        if (oq.isPresent()) { // isPresent로 null이 아닌지를 확인한 후
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
         */
        /*
        // findBySubject, Subject값으로 데이터 조회
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
         */
        /*
        // findBySubjectAndContent, 제목과 내용을 함께 조회
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다."
        );
        assertEquals(1, q.getId());
        */
        /*
        // findBySubjectLike, 제목에 특정 문자열이 포함되어 있는 데이터를 조회
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
        */
    }
}
