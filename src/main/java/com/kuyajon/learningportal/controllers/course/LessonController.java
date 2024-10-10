package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.CourseDTO;
import com.kuyajon.learningportal.dto.course.LessonDTO;
import com.kuyajon.learningportal.model.course.Course;
import com.kuyajon.learningportal.model.course.Lesson;
import com.kuyajon.learningportal.repository.course.CourseRepository;
import com.kuyajon.learningportal.repository.course.LessonRepository;
import com.kuyajon.learningportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses/{courseId}/lessons")
@CrossOrigin(origins = "*")
public class LessonController {

    @Autowired
    private CourseService courseService;

    /*
     * used to handle HTTP GET requests in a RESTful web service using the provided
     * id.
     * gets the lesson from the db based on the provided id.
     */
    @GetMapping("/{id}")
    public Optional<LessonDTO> getLessonById(@PathVariable Long courseId, @PathVariable Long id) {
        return courseService.getLessonByID(id);
    }

    /*
     * used to handle HTTP GET requests in a RESTful web service.
     * gets all the lesson from the db based on course ID.
     */
    @GetMapping
    public List<LessonDTO> getAllLessonByCourseId(@PathVariable Long courseId) {
        return courseService.getLessonsByCourseId(courseId);
    }

    /*
     * maps HTTP POST requests to this method.
     * create a lesson.
     */
    @PostMapping
    public LessonDTO createLesson(@PathVariable Long courseId, @RequestBody LessonDTO lessonDTO) {
        lessonDTO.setCourseId(courseId);
        return courseService.saveOrUpdateLesson(lessonDTO);
    }

    @PutMapping("/{id}")
    public LessonDTO updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        lessonDTO.setId(id);
        return courseService.saveOrUpdateLesson(lessonDTO);
    }

    /*
     * used to handle HTTP DELETE requests in a RESTful web service.
     * delete the lesson based on lesson ID.
     */
    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        Optional<LessonDTO> lessonOptional = courseService.getLessonByID(id);
        if (lessonOptional.isPresent()) {
            courseService.deleteLessonById(id);
        } else {
            throw new IllegalArgumentException("No lesson ID found.");
        }
    }

}
