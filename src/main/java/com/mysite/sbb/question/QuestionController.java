package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    // 스프링 의존성 주입 규칙에 의해 questionRepository 객체가 자동으로 주입
    private final QuestionRepository questionRepository;

    /*
    // 템플릿의 내용을 화면에 전달하는 것은 성공
    @GetMapping("/question/list")
//    @ResponseBody
    public String list() {
        return "question_list"; // 문자열이 아닌 뷰의 이름
    }
     */

    // 데이터 조회하여 템플릿에 전달하기
    @GetMapping("/question/list")
    public String list(Model model) { // Model 객체는 자바 클래스와 템플릿 간의 연결고리 역할
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
