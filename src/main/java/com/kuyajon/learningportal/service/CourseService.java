package com.kuyajon.learningportal.service;

import com.kuyajon.learningportal.dto.course.CourseDTO;
import com.kuyajon.learningportal.dto.course.LessonDTO;
import com.kuyajon.learningportal.model.client.*;
import com.kuyajon.learningportal.model.course.*;
import com.kuyajon.learningportal.repository.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TopicRepository topicRepository;

    // Retrieve all entity course.
    public List<CourseDTO> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> result = new ArrayList<CourseDTO>();
        for (Course course : courses) {
            CourseDTO dto = convertToDTO(course);
            result.add(dto);
        }
        return result;

    }

    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public List<Test> getAllTest() {
        return testRepository.findAll();
    }

    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }

    // Retrieve course by id.
    public Optional<CourseDTO> getCourseByID(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            CourseDTO result = convertToDTO(course);
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    public Optional<LessonDTO> getLessonByID(Long id) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isPresent()) {
            Lesson lesson = lessonOptional.get();
            LessonDTO lessonDTO = convertToDTO(lesson);
            return Optional.of(lessonDTO);
        } else {
            throw new IllegalArgumentException("Lesson ID must not be null");
        }
    }

    public Optional<Question> getQuestionByID(Long id) {
        return Optional.of(questionRepository.findById(id).get());
    }

    public Optional<Test> getTestByID(Long id) {
        return Optional.of(testRepository.findById(id).get());
    }

    public Optional<Topic> getTopicByID(Long id) {
        return Optional.of(topicRepository.findById(id).get());
    }

    // Save or update course obj.
    public CourseDTO saveOrUpdateCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        courseRepository.save(course);
        return convertToDTO(course);
    }

    public LessonDTO saveOrUpdateLesson(LessonDTO lessonDTO) {
        Lesson lesson = convertToEntity(lessonDTO);
        lessonRepository.save(lesson);
        return convertToDTO(lesson);
    }

    public Question saveOrUpdateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Test saveOrUpdateTest(Test test) {
        return testRepository.save(test);
    }

    public Topic saveOrUpdateTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    // Delete client obj.
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    public void deleteLessonById(Long id) {
        lessonRepository.deleteById(id);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.deleteById(id);
    }

    public void deleteTestById(Long id) {
        testRepository.deleteById(id);
    }

    public void deleteTopicById(Long id) {
        topicRepository.deleteById(id);
    }

    public List<LessonDTO> getLessonsByCourseId(Long courseId) {
        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);
        List<LessonDTO> result = new ArrayList<LessonDTO>();

        for (Lesson lesson : lessons) {
            LessonDTO dto = convertToDTO(lesson);
            result.add(dto);
        }
        return result;
    }

    public List<Question> getQuestionsByTestId(Long testId) {
        return questionRepository.findByTestId(testId);
    }

    public List<Test> getTestByLessonId(Long lessonId) {
        return testRepository.findTestByLessonId(lessonId);
    }

    public List<Test> getTestByTopicId(Long topicId) {
        return testRepository.findTestByTopicId(topicId);
    }

    public List<Topic> getTopicByLessonId(Long lessonId) {
        return topicRepository.findTopicByLessonId(lessonId);
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
        if (courseDTO.getId() != null && courseDTO.getId().longValue() > 0) {
            course = courseRepository.getReferenceById(courseDTO.getId());
        }
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        return course;

    }

    public LessonDTO convertToDTO(Lesson lesson) {
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

    private Lesson convertToEntity(LessonDTO lessonDTO) {
        Lesson lesson;
        if (lessonDTO.getId() == null || lessonDTO.getId().longValue() == 0) {
            lesson = new Lesson();
            Optional<Course> courseOptional = courseRepository.findById(lessonDTO.getCourseId());
            if (courseOptional.isPresent()) {
                lesson.setCourse(courseOptional.get());
            }
        } else {
            lesson = lessonRepository.getReferenceById(lessonDTO.getId());
        }
        lesson.setName(lessonDTO.getName());
        lesson.setDescription(lessonDTO.getDescription());
        return lesson;
    }

}
