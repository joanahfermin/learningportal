package com.kuyajon.learningportal.repository.client;

import com.kuyajon.learningportal.model.client.LessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {
}
