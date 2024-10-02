package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.TestDTO;
import com.kuyajon.learningportal.model.course.Lesson;
import com.kuyajon.learningportal.model.course.Test;
import com.kuyajon.learningportal.repository.course.TestRepository;
import com.kuyajon.learningportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private CourseService courseService;

    //getAllTest - done
    //getTestById - done 
    //getAllTestByLessonId - done
    //getAllTestByTopicId - done

    @GetMapping
    public List<TestDTO> getAllTest(){
        List<Test> tests = courseService.getAllTest();
        List<TestDTO> result = new ArrayList<TestDTO>();

        for (Test test : tests){
            TestDTO testDTO = convertToDTO(test);
            result.add(testDTO);
        }
        return result;
    }

    @GetMapping("/{id}")
    public Optional<TestDTO> getTestById (@PathVariable Long id){
        Optional<Test> testOptional = courseService.getTestByID(id);

        if (testOptional.isPresent()) {
            Test test = testOptional.get();
            TestDTO testDTO = convertToDTO(test);
            return Optional.of(testDTO);
        } else {
            throw new IllegalArgumentException("Test ID must not be null");
        }
    }

    @GetMapping("/lesson/{lessonId}")
    public List<TestDTO> getAllTestByLessonId (@PathVariable Long lessonId){
        List<Test> tests = courseService.getTestByLessonId(lessonId);
        List<TestDTO> result = new ArrayList<TestDTO>();

        if (tests.isEmpty()) {
            throw new IllegalArgumentException("Lesson ID must not be null");
        }

        for (Test test : tests) {
            TestDTO testDTO = convertToDTO(test);
            result.add(testDTO);
        }

        return result;
    }

    @GetMapping("/topic/{topicId}")
    public List<TestDTO> getAllTestByTopicId (@PathVariable Long topicId){
        List<Test> topics = courseService.getTestByLessonId(topicId);
        List<TestDTO> result = new ArrayList<TestDTO>();

        if (topics.isEmpty()) {
            throw new IllegalArgumentException("Topic ID must not be null");
        }

        for (Test test : topics) {
            TestDTO testDTO = convertToDTO(test);
            result.add(testDTO);
        }
        return result;
    }

    //createTest
    //updateTest
    //deleteTest - done 

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id){
        Optional<Test> test = courseService.getTestByID(id);
        if (test.isPresent()) {
            courseService.deleteTestById(id);
        } else {
            throw new IllegalArgumentException("No test ID found.");
        }
    }

    private TestDTO convertToDTO(Test test){
        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        testDTO.setName(test.getName());

        // Assuming lesson.getCourse() returns a Course object
        if (test.getLesson() != null && test.getLesson().getId() != null) {
            testDTO.setLessonId(test.getLesson().getId());
        } else {
            testDTO.setLessonId(null); // or handle the case where the course is null
        }

        if (test.getTopic() != null && test.getTopic().getId() != null) {
            testDTO.setTopicId(test.getTopic().getId());
        } else {
            testDTO.setTopicId(null); // Handle case where topic or topic.getId() is null
        }

        return testDTO;
    }
}
