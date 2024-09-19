package com.kuyajon.learningportal.repository.client;

import com.kuyajon.learningportal.model.client.LessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {
}
