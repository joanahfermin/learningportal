package com.kuyajon.learningportal.controllers.course;

import com.kuyajon.learningportal.dto.course.CourseDTO;
import com.kuyajon.learningportal.model.course.Course;
import com.kuyajon.learningportal.repository.course.CourseRepository;
import com.kuyajon.learningportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseService.getAllCourse();
        List<CourseDTO> result = new ArrayList<CourseDTO>();
        for(Course course:courses) {
            CourseDTO dto = convertToDTO(course);
            /*new CourseDTO();
            dto.setId(course.getId());
            dto.setName(course.getName());
            dto.setDescription(course.getDescription());*/
            result.add(dto);
        }
        return result;
        /*
        return courseRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());*/
    }

    @GetMapping("/{id}")
    public Optional<CourseDTO> getCourseById(@PathVariable Long id) {
//        Optional<Course> courseOptional = courseRepository.findById(id);
        Optional<Course> courseOptional = courseService.getCourseByID(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            CourseDTO result = convertToDTO(course);
//            result.setId(course.getId());
//            result.setName(course.getName());
//            result.setDescription(course.getDescription());
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
        /*return courseRepository.findById(id)
            .map(this::convertToDTO); */
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        course = courseRepository.save(course);
        return convertToDTO(course);
    }

    @PutMapping("/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        Optional<Course> courseOptional = courseService.getCourseByID(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setDescription(courseDTO.getDescription());
            course.setName(courseDTO.getName());
            course = courseRepository.save(course);
            return convertToDTO(course);
        } else {
            throw new IllegalArgumentException("Course ID must not be null");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
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
