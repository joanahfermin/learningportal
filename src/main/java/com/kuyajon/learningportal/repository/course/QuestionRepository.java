package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
