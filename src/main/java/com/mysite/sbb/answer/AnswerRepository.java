package com.mysite.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AnswerRepository를 이용하여 answer 테이블에 데이터를 저장하거나 조회할 수 있다.
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
