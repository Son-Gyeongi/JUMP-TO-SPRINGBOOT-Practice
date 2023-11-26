package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 리포지터리는 엔티티에 의해 생성된 데이터베이스 테이블에
 * 접근하는 메서드들(예: findAll, save 등)을 사용하기 위한 인터페이스
 * <p>
 * QuestionRepository를 이용하여 question테이블에 데이터를 저장하거나 조회할 수 있다.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // findBySubject, Subject값으로 데이터 조회
    Question findBySubject(String subject);

    // findBySubjectAndContent, 제목과 내용을 함께 조회
    Question findBySubjectAndContent(String subject, String content);

    // findBySubjectLike, 제목에 특정 문자열이 포함되어 있는 데이터를 조회
    List<Question> findBySubjectLike(String subject);

    // 페이징 구현하기
    Page<Question> findAll(Pageable pageable);

    // 검색
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    // 쿼리로 검색하기
    @Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
