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
@RequestMapping("/api/courses/{courseId}")
@CrossOrigin(origins = "*")
public class LessonController {

    // tells Spring to automatically "inject" or "provide" an instance of a class
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    /*
    used to handle HTTP GET requests in a RESTful web service using the provided id.
    gets the lesson from the db based on the provided id.
     */
    @GetMapping("/lessons/{id}")
    public Optional<LessonDTO> getLessonById(@PathVariable Long courseId, @PathVariable Long id) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);

        if (lessonOptional.isPresent()) {
            Lesson lesson = lessonOptional.get();
            LessonDTO lessonDTO = convertToLesson(lesson);
            return Optional.of(lessonDTO);
        } else {
            throw new IllegalArgumentException("Lesson ID must not be null");
        }
    }

    /*
    used to handle HTTP GET requests in a RESTful web service.
    gets all the lesson from the db based on course ID.
     */
    @GetMapping("/lessons")
    public List<LessonDTO> getAllLessonByCourseId(@PathVariable Long courseId) {
        List<Lesson> lessons = courseService.getLessonsByCourseId(courseId);;
        List<LessonDTO> result = new ArrayList<LessonDTO>();

        if (lessons.isEmpty()) {
            throw new IllegalArgumentException("Lesson ID must not be null");
        }
        for (Lesson lesson:lessons){
            LessonDTO dto = convertToLesson(lesson);
            result.add(dto);
        }
        return result;
    }

    @PutMapping("/lessons/{id}")
    public LessonDTO updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);;
        if (lessonOptional.isPresent()) {
            Lesson lesson = lessonOptional.get();
            lesson.setDescription(lessonDTO.getDescription());
            lesson.setName(lessonDTO.getName());
            lesson = lessonRepository.save(lesson);
            return convertToLesson(lesson);
        } else {
            throw new IllegalArgumentException("Course ID must not be null");
        }
    }

    /*
    maps HTTP POST requests to this method.
    create a lesson.
     */
    @PostMapping
    private LessonDTO createLesson(@RequestBody LessonDTO lessonDTO){
        Optional<Course> courseOptional = courseRepository.findById(lessonDTO.getCourseId());
        Course course = courseOptional.get();
        Lesson lesson = new Lesson();
        lesson.setName(lessonDTO.getName());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return lessonDTO;
    }

    /*
    used to handle HTTP DELETE requests in a RESTful web service.
    delete the lesson based on lesson ID.
     */
    @DeleteMapping("/lessons/{id}")
    public void deleteCourseById(@PathVariable Long id){
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isPresent()) {
            lessonRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No lesson ID found.");
        }
    }

    // converts lesson to lessonDTO
    public LessonDTO convertToLesson(Lesson lesson){
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setName(lesson.getName());
        lessonDTO.setDescription(lesson.getDescription());

        // Assuming lesson.getCourse() returns a Course object
        if (lesson.getCourse() != null) {
            lessonDTO.setCourseId(lesson.getCourse().getId()); // Get the ID from the Course object
        } else {
            lessonDTO.setCourseId(null); // or handle the case where the course is null
        }
        return lessonDTO;
    }
}
