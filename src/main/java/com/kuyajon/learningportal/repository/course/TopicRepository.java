package com.kuyajon.learningportal.repository.course;

import com.kuyajon.learningportal.model.course.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findTopicByLessonId(Long id);
}
