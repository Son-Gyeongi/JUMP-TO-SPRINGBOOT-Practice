package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/question")
@Controller
@RequiredArgsConstructor
public class QuestionController {

    // 스프링 의존성 주입 규칙에 의해 questionRepository 객체가 자동으로 주입
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final UserService userService;

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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    /*
    @PreAuthorize("isAuthenticated()") 애너테이션이 붙은 메서드는 로그인이 필요한 메서드를 의미
    애너테이션이 적용된 메서드가 로그아웃 상태에서 호출되면 로그인 페이지로 이동
     */
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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 Principal principal) {
        // BindingResult매개변수는 @Valid 애너테이션으로 인해 검증이 수행된 결과를 의미하는 객체
        if (bindingResult.hasErrors()) { // 오류가 있는 경우
            return "question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());

        // 질문을 저장한다.
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,
                                 @PathVariable("id") Integer id,
                                 Principal principal) {
        // 질문 객체 찾기
        Question question = this.questionService.getQuestion(id);
        // 현재 로그인한 사용자와 질문의 작성자가 동일하지 않을 경우
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
        }
        // 질문자와 회원이 일치하면
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

    // 질문 수정 POST
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, // questionForm의 데이터를 검증
                                 BindingResult bindingResult,
                                 Principal principal,
                                 @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        // 로그인한 사용자와 수정하려는 질문의 작성자가 동일한지도 검증
        Question question = this.questionService.getQuestion(id);

        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        // QuestionService에서 작성한 modify 메서드를 호출
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id); // 질문 상세 화면을 다시 호
    }

    // 질문 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal,
                                 @PathVariable("id") Integer id) {
        // URL로 전달받은 id값을 사용하여 Question 데이터를 조회한후
        Question question = this.questionService.getQuestion(id);

        // 로그인한 사용자와 질문 작성자가 동일하지 않은 경우
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.questionService.delete(question);

        return "redirect:/"; // 질문 데이터 삭제 후에는 질문 목록 화면
    }

    // 추천인 저장
    // 추천은 로그인한 사람만 가능해야 하므로 @PreAuthorize("isAuthenticated()") 애너테이션이 적용
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.vote(question, siteUser);
        return String.format("redirect:/question/detail/%s", id); // 오류가 없다면 질문 상세화면으로 리다이렉트
    }
}
