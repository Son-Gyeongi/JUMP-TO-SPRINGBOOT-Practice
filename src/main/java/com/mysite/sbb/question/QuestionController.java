package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController {

    // 스프링 의존성 주입 규칙에 의해 questionRepository 객체가 자동으로 주입
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    /*
    // 템플릿의 내용을 화면에 전달하는 것은 성공
    @GetMapping("/question/list")
//    @ResponseBody
    public String list() {
        return "question_list"; // 문자열이 아닌 뷰의 이름
    }
     */

    // 데이터 조회하여 템플릿에 전달하기
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        // Model 객체는 자바 클래스와 템플릿 간의 연결고리 역할

        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    // 질문 등록하기 버튼
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        /*
        QuestionForm과 같이 매개변수로 바인딩한 객체는 Model 객체로 전달하지 않아도 템플릿에서 사용이 가능
         */
        return "question_form";
    }

    /*
    메서드명은 @GetMapping시 사용했던 questionCreate 메서드명과 동일하게 사용할 수 있다.
    (단, 매개변수의 형태가 다른 경우에 가능하다. - 메서드 오버로딩)
     */
    // 질문 저장
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        // BindingResult매개변수는 @Valid 애너테이션으로 인해 검증이 수행된 결과를 의미하는 객체
        if (bindingResult.hasErrors()) { // 오류가 있는 경우
            return "question_form";
        }

        // 질문을 저장한다.
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
    }
}
