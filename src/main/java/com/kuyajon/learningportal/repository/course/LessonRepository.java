package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
