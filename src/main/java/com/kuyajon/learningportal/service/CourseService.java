package com.kuyajon.learningportal.service;

import com.kuyajon.learningportal.model.client.*;
import com.kuyajon.learningportal.model.course.*;
import com.kuyajon.learningportal.repository.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    //Retrieve all entity course.
    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public List<Lesson> getAllLesson(){
        return lessonRepository.findAll();
    }

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public List<Test> getAllTest(){
        return testRepository.findAll();
    }


    //Retrieve course by id.
    public Optional<Course> getCourseByID(Long id){
        return Optional.of(courseRepository.findById(id).get());
    }

    public Optional<Lesson> getLessonByID(Long id){
        return Optional.of(lessonRepository.findById(id).get());
    }

    public Optional<Question> getQuestionByID(Long id){
        return Optional.of(questionRepository.findById(id).get());
    }

    public Optional<Test> getTestByID(Long id){
        return Optional.of(testRepository.findById(id).get());
    }

    public Topic getTopicID(Long id){
        return topicRepository.findById(id).get();
    }





    //Save or update course obj.
    public Course saveOrUpdateCourse(Course course){
        return courseRepository.save(course);
    }

    public Lesson saveOrUpdateLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    public Question saveOrUpdateQuestion(Question question){
        return questionRepository.save(question);
    }

    public Test saveOrUpdateTest(Test test){
        return testRepository.save(test);
    }

    public Topic saveOrUpdateTopic(Topic topic){
        return topicRepository.save(topic);
    }



    //Delete client obj.
    public void deleteCourseById(Long id){
        courseRepository.deleteById(id);
    }

    public void deleteLessonById(Long id){
        lessonRepository.deleteById(id);
    }

    public void deleteQuestionById(Long id){
        questionRepository.deleteById(id);
    }

    public void deleteTestById(Long id){
        testRepository.deleteById(id);
    }

    public void deleteTopicById(Long id){
        topicRepository.deleteById(id);
    }




    public List<Lesson> getLessonsByCourseId(Long courseId){
        return lessonRepository.findByCourseId(courseId);
    }

    public List<Question> getQuestionsByTestId(Long testId){
        return questionRepository.findByTestId(testId);
    }

    public List<Test> getTestByLessonId(Long lessonId){
        return testRepository.findTestByLessonId(lessonId);
    }

    public List<Test> getTestByTopicId(Long topicId){
        return testRepository.findTestByTopicId(topicId);
    }
}
