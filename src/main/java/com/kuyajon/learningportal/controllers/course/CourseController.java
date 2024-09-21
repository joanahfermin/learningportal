package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.CourseDTO;
import com.kuyajon.learningportal.model.course.Course;
import com.kuyajon.learningportal.repository.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<CourseDTO> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
            .map(this::convertToDTO);
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        course = courseRepository.save(course);
        return convertToDTO(course);
    }

    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        course.setId(id);
        course = courseRepository.save(course);
        return convertToDTO(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        return courseDTO;
    }

    private Course convertToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        return course;
    }
}
