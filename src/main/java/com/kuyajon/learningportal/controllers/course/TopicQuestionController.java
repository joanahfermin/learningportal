package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.QuestionDTO;
import com.kuyajon.learningportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses/{courseId}/lessons/{lessonId}/topics/{topicId}/questions")
@CrossOrigin(origins = "*")
public class TopicQuestionController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public Optional<QuestionDTO> getQuestionById(@PathVariable Long id) {
        return courseService.getQuestionByID(id);
    }

    @GetMapping
    public List<QuestionDTO> getAllQuestionsByTopicId(@PathVariable Long topicId) {
        return courseService.getQuestionsByTopicId(topicId);
    }

    @PostMapping()
    public QuestionDTO createQuestion(@PathVariable Long topicId, @RequestBody QuestionDTO questionDTO) {
        questionDTO.setTopicId(topicId);
        return courseService.saveOrUpdateQuestion(questionDTO);
    }

    @PutMapping("/{id}")
    public QuestionDTO updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        questionDTO.setId(id);
        return courseService.saveOrUpdateQuestion(questionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        Optional<QuestionDTO> questionOptional = courseService.getQuestionByID(id);
        if (questionOptional.isPresent()) {
            courseService.deleteQuestionById(id);
        } else {
            throw new IllegalArgumentException("No question ID found.");
        }
    }
}
