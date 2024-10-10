package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.LessonDTO;
import com.kuyajon.learningportal.dto.course.TopicDTO;
import com.kuyajon.learningportal.model.course.Lesson;
import com.kuyajon.learningportal.model.course.Topic;
import com.kuyajon.learningportal.repository.course.TopicRepository;
import com.kuyajon.learningportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses/{courseId}/lessons/{lessonId}/topics")
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public Optional<TopicDTO> getTopicById(@PathVariable Long id) {
        return courseService.getTopicByID(id);
    }

    @GetMapping
    public List<TopicDTO> getAllTopicByLessonId(@PathVariable Long lessonId) {
        return courseService.getTopicsByLessonId(lessonId);
    }

    @PostMapping()
    public TopicDTO createTopic(@PathVariable Long lessonId, @RequestBody TopicDTO topicDTO) {
        topicDTO.setLessonId(lessonId);
        return courseService.saveOrUpdateTopic(topicDTO);
    }

    @PutMapping("/{id}")
    public TopicDTO updateTopic(@PathVariable Long id, @RequestBody TopicDTO topicDTO) {
        topicDTO.setId(id);
        return courseService.saveOrUpdateTopic(topicDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTopic(@PathVariable Long id) {
        Optional<TopicDTO> topicOptional = courseService.getTopicByID(id);
        if (topicOptional.isPresent()) {
            courseService.deleteTopicById(id);
        } else {
            throw new IllegalArgumentException("No topic ID found.");
        }
    }

}
