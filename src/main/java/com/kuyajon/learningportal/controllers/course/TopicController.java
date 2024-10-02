package com.kuyajon.learningportal.controllers.course;


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
@RequestMapping("/api/topic")
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseService courseService;

    //getAllTopic - done
    //getTopicById - done
    //getAllTopicByLessonId - done

    @GetMapping()
    public List<TopicDTO> getAllTopic(){
        List<Topic> topics = courseService.getAllTopic();
        List<TopicDTO> result = new ArrayList<TopicDTO>();

        for (Topic topic : topics){
            TopicDTO topicDTO = convertToDTO(topic);
            result.add(topicDTO);
        }
        return result;
    }

    @GetMapping("/{id}")
    public Optional<TopicDTO> getTopicById(@PathVariable Long id){
        Optional<Topic> topicOptional = courseService.getTopicByID(id);

        if (topicOptional.isPresent()){
            Topic topic = topicOptional.get();
            TopicDTO topicDTO = convertToDTO(topic);
            return Optional.of(topicDTO);
        } else {
            throw new IllegalArgumentException("Topic ID must not be null");
        }
    }

    @GetMapping("/lesson/{lessonId}")
    public List<TopicDTO> getAllTopicByLessonId(@PathVariable Long lessonId){
        List<Topic> topics = courseService.getTopicByLessonId(lessonId);
        List<TopicDTO> result = new ArrayList<TopicDTO>();

        if (topics.isEmpty()) {
            throw new IllegalArgumentException("Lesson ID must not be null");
        }

        for (Topic topic : topics){
            TopicDTO topicDTO = convertToDTO(topic);
            result.add(topicDTO);
        }
        return result;
    }

    //createTopic - done 
    //updateTopic
    //deleteTopic

    @PostMapping()
    public TopicDTO createTopic(@RequestBody TopicDTO topicDTO){
        Optional<Lesson> lessonOptional = courseService.getLessonByID(topicDTO.getLessonId());

        if (lessonOptional.isPresent()){
            Lesson lesson = lessonOptional.get();
            Topic topic = new Topic();
            topic.setName(topicDTO.getName());
            topic.setContent(topicDTO.getContent());
            topic.setLesson(lesson);
            Topic topicSaved = courseService.saveOrUpdateTopic(topic);
            return convertToDTO(topicSaved);
        }

        return null;
    }

    private TopicDTO convertToDTO(Topic topic){
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setName(topic.getName());
        topicDTO.setContent(topic.getContent());

        if (topic.getLesson() != null && topic.getLesson().getId() != null){
            topicDTO.setLessonId(topic.getId());
        } else {
            topicDTO.setLessonId(null);
        }
        return topicDTO;
    }
}
