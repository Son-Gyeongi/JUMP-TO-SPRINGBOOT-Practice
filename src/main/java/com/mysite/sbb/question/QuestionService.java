package com.mysite.sbb.question;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 컨트롤러에서 리포지터리를 직접 호출하지 않고 중간에 서비스(Service)를 두어 데이터를 처리한다.
 * 서비스는 스프링에서 데이터 처리를 위해 작성하는 클래스이다.
 * 스프링부트는 @Service 애너테이션이 붙은 클래스는 서비스로 인식
 */
@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    // 질문 목록을 조회하여 리턴
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    // 데이터의 실제 제목과 내용을 출력해 보자.
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);

        // Optional 객체이기 때문에 위와 같이 isPresent 메서드로
        // 해당 데이터가 존재하는지 검사하는 로직이 필요
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
}
