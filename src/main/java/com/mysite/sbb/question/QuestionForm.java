package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 질문이나 내용을 등록할 때 비어 있는 값으로 등록이 가능하다는 점
 * 폼을 사용하여 입력값을 체크하는 방법을 사용
 * 화면에서 전달되는 입력 값을 검증하기 위해서는 폼 클래스가 필요
 * <p>
 * 폼 클래스는 입력 값의 검증에도 사용하지만 화면에서 전달한 입력 값을 바인딩할 때에도 사용한다.
 */
@Setter
@Getter
public class QuestionForm {
    // @NotEmpty는 해당 값이 Null 또는 빈 문자열("")을 허용하지 않음
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200) // 최대 길이가 200 바이트를 넘으면 안된다
    private String subject;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
