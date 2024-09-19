package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
