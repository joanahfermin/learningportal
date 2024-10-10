package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Question;
import com.kuyajon.learningportal.model.course.Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionByTopicId(Long id);

    List<Question> findQuestionByLessonId(Long id);
}
