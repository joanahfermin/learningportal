package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findTestByLessonId(Long id);

    List<Test> findTestByTopicId(Long id);
}
