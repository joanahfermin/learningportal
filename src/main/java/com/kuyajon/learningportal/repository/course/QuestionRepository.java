package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTestId(Long id);
}
