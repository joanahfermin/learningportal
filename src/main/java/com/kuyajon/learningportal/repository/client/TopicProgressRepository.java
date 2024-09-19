package com.kuyajon.learningportal.repository.client;

import com.kuyajon.learningportal.model.client.TopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicProgressRepository extends JpaRepository<TopicProgress, Long> {
}
