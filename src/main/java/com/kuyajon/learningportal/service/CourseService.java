package com.kuyajon.learningportal.service;

import com.kuyajon.learningportal.model.client.*;
import com.kuyajon.learningportal.model.course.*;
import com.kuyajon.learningportal.repository.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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



    //Retrieve course by id.
    public Course getCourseByID(Long id){
        return courseRepository.findById(id).get();
    }

    public Lesson getLessonByID(Long id){
        return lessonRepository.findById(id).get();
    }

    public Question getQuestionByID(Long id){
        return questionRepository.findById(id).get();
    }

    public Test getTestByID(Long id){
        return testRepository.findById(id).get();
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
}
