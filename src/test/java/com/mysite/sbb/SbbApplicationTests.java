package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional // 메서드가 종료될 때까지 DB 세션이 유지된다.
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
        /*
        // 데이터 수정
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
         */
        /*
        // 데이터 삭제하기
        // 리포지터리의 count() 메서드는 해당 리포지터리의 총 데이터건수를 리턴
        assertEquals(2, questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
         */

        // 답변 //
        /*
        // 답변 데이터 생성 후 저장하기
        Optional<Question> oq = this.questionRepository.findById(2);
        Assertions.assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q); // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
         */
        /*
        // 답변 조회
        Optional<Answer> oa = this.answerRepository.findById(1);
        Assertions.assertTrue(oa.isPresent());
        Answer a = oa.get();
        Assertions.assertEquals(2, a.getQuestion().getId());
         */

        // 답변에 연결된 질문 찾기 vs 질문에 달린 답변 찾기
        // 질문 객체로부터 답변 리스트를 구하는 테스트코드
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }
}
