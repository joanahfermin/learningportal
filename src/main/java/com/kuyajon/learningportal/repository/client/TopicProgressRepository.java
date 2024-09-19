package com.kuyajon.learningportal.repository.client;

import com.kuyajon.learningportal.model.client.TopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicProgressRepository extends JpaRepository<TopicProgress, Long> {
}
