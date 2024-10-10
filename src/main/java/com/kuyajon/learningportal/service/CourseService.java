package com.kuyajon.learningportal.service;

import com.kuyajon.learningportal.dto.course.CourseDTO;
import com.kuyajon.learningportal.dto.course.LessonDTO;
import com.kuyajon.learningportal.dto.course.QuestionDTO;
import com.kuyajon.learningportal.dto.course.TopicDTO;
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

    public Optional<QuestionDTO> getQuestionByID(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            Question q = questionOptional.get();
            QuestionDTO result = convertToDTO(q);
            return Optional.of(result);
        } else {
            return Optional.empty();
        }

    }

    public Optional<TopicDTO> getTopicByID(Long id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if (topicOptional.isPresent()) {
            Topic topic = topicOptional.get();
            TopicDTO result = convertToDTO(topic);
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
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

    public QuestionDTO saveOrUpdateQuestion(QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        questionRepository.save(question);
        return convertToDTO(question);
    }

    public TopicDTO saveOrUpdateTopic(TopicDTO topicDTO) {
        Topic topic = convertToEntity(topicDTO);
        topicRepository.save(topic);
        return convertToDTO(topic);
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

    public List<TopicDTO> getTopicsByLessonId(Long lessonId) {
        List<Topic> topics = topicRepository.findTopicByLessonId(lessonId);
        List<TopicDTO> result = new ArrayList<TopicDTO>();

        for (Topic topic : topics) {
            TopicDTO dto = convertToDTO(topic);
            result.add(dto);
        }
        return result;
    }

    public List<QuestionDTO> getQuestionsByTopicId(Long topicId) {
        List<Question> questions = questionRepository.findQuestionByTopicId(topicId);
        List<QuestionDTO> result = new ArrayList<QuestionDTO>();

        for (Question q : questions) {
            QuestionDTO dto = convertToDTO(q);
            result.add(dto);
        }
        return result;
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

    private TopicDTO convertToDTO(Topic topic) {
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setName(topic.getName());
        topicDTO.setContent(topic.getContent());

        if (topic.getLesson() != null && topic.getLesson().getId() != null) {
            topicDTO.setLessonId(topic.getId());
        } else {
            topicDTO.setLessonId(null);
        }
        return topicDTO;
    }

    private Topic convertToEntity(TopicDTO topicDTO) {
        Topic topic;
        if (topicDTO.getId() == null || topicDTO.getId().longValue() == 0) {
            topic = new Topic();
            Optional<Lesson> lessonOptional = lessonRepository.findById(topicDTO.getLessonId());
            if (lessonOptional.isPresent()) {
                topic.setLesson(lessonOptional.get());
            }
        } else {
            topic = topicRepository.getReferenceById(topicDTO.getId());
        }
        topic.setName(topicDTO.getName());
        topic.setContent(topicDTO.getContent());
        return topic;
    }

    private QuestionDTO convertToDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setChoiceA(question.getChoiceA());
        questionDTO.setChoiceB(question.getChoiceB());
        questionDTO.setChoiceC(question.getChoiceC());
        questionDTO.setChoiceD(question.getChoiceD());
        questionDTO.setAnswer(question.getAnswer());
        questionDTO.setSolution(question.getSolution());

        if (question.getTopic() != null && question.getTopic().getId() != null) {
            questionDTO.setTopicId(question.getTopic().getId());
        } else {
            questionDTO.setTopicId(null);
        }

        if (question.getLesson() != null && question.getLesson().getId() != null) {
            questionDTO.setLessonId(question.getLesson().getId());
        } else {
            questionDTO.setLessonId(null);
        }

        return questionDTO;
    }

    private Question convertToEntity(QuestionDTO questionDTO) {
        Question question;

        if (questionDTO.getId() == null || questionDTO.getId().longValue() == 0) {
            // Create new Question entity if no ID or ID is 0
            question = new Question();

            // Set Topic if topicId is provided
            Optional<Topic> topicOptional = topicRepository.findById(questionDTO.getTopicId());
            if (topicOptional.isPresent()) {
                question.setTopic(topicOptional.get());
            }

            // Set Lesson if lessonId is provided
            Optional<Lesson> lessonOptional = lessonRepository.findById(questionDTO.getLessonId());
            if (lessonOptional.isPresent()) {
                question.setLesson(lessonOptional.get());
            }
        } else {
            // Get existing Question entity from repository if ID exists
            question = questionRepository.getReferenceById(questionDTO.getId());
        }

        // Set basic fields
        question.setQuestionText(questionDTO.getQuestionText());
        question.setChoiceA(questionDTO.getChoiceA());
        question.setChoiceB(questionDTO.getChoiceB());
        question.setChoiceC(questionDTO.getChoiceC());
        question.setChoiceD(questionDTO.getChoiceD());
        question.setAnswer(questionDTO.getAnswer());
        question.setSolution(questionDTO.getSolution());

        return question;
    }

}
