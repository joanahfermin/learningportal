package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
