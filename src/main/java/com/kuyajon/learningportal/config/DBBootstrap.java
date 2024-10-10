package com.kuyajon.learningportal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kuyajon.learningportal.repository.course.CourseRepository;
import com.kuyajon.learningportal.repository.course.LessonRepository;
import com.kuyajon.learningportal.repository.course.QuestionRepository;
import com.kuyajon.learningportal.repository.course.TestRepository;
import com.kuyajon.learningportal.repository.course.TopicRepository;
import com.kuyajon.learningportal.repository.client.ClientRepository;
import com.kuyajon.learningportal.repository.client.ClientGroupRepository;
import com.kuyajon.learningportal.model.course.AnswerChoice;
import com.kuyajon.learningportal.model.course.Course;
import com.kuyajon.learningportal.model.course.Lesson;
import com.kuyajon.learningportal.model.course.Question;
import com.kuyajon.learningportal.model.course.Test;
import com.kuyajon.learningportal.model.course.Topic;
import com.kuyajon.learningportal.model.client.Client;
import com.kuyajon.learningportal.model.client.ClientGroup;
import com.kuyajon.learningportal.model.sys.ERole;
import com.kuyajon.learningportal.model.sys.User;
import com.kuyajon.learningportal.repository.sys.UserRepository;
import com.kuyajon.learningportal.service.SecurityService;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DBBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(DBBootstrap.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientGroupRepository clientGroupRepository;

    @Autowired
    private ProfileConfig profileConfig;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        if (profileConfig.getEnvironment() == EEnvironment.DEV) {
            createInitialDataForDev();
        } else if (profileConfig.getEnvironment() == EEnvironment.PROD) {
            createInitialDataForProd();
        }
    }

    private void createInitialDataForDev() {
        LOG.info("Creating Initial Data for DEV");
        createDevUsers();
        createDevCourses();
        createClientGroups();
        LOG.info("Creating Initial Data for DEV - Done");
    }

    private void createInitialDataForProd() {
        LOG.info("Creating Initial Data for PROD");
        LOG.info("Creating Initial Data for PROD - Done");
    }

    private void createDevUsers() {
        if (userRepository.count() == 0) {
            securityService.createUser("admin", "admin", ERole.ADMIN);
            securityService.createUser("stu", "stu", ERole.STUDENT);
        }
    }

    private void createDevCourses() {
        if (courseRepository.count() == 0) {
            for (int c = 0; c < 5; c++) {
                Course course = new Course();
                course.setName("Course " + c);
                course.setDescription("The quick brown course.");
                courseRepository.save(course);
                for (int l = 0; l < 5; l++) {
                    Lesson lesson = new Lesson();
                    lesson.setName("Course " + c + " Lesson " + l);
                    lesson.setDescription("The quick brown lesson.");
                    lesson.setSortOrder(l);
                    lesson.setCourse(course);
                    lessonRepository.save(lesson);
                    createTestObject(lesson, null);

                    for (int t = 0; t < 3; t++) {
                        Topic topic = new Topic();
                        topic.setName("Topic " + t);
                        topic.setContent("**Welcome to topic!**\nHello *world*");
                        topic.setSortOrder(t);
                        topic.setLesson(lesson);
                        topicRepository.save(topic);
                        createTestObject(null, topic);
                    }
                }
            }
        }
    }

    private void createTestObject(Lesson lesson, Topic topic) {
        Test test = new Test();
        test.setName("Test " + UUID.randomUUID());
        test.setLesson(lesson);
        test.setTopic(topic);
        testRepository.save(test);

        for (int q = 0; q < 5; q++) {
            Question question = new Question();
            question.setQuestionText("What is?");
            question.setChoiceA("Is it A");
            question.setChoiceB("Is it B");
            question.setChoiceC("Is it C");
            question.setChoiceD("Is it D");
            question.setAnswer(AnswerChoice.B);
            question.setSolution("Here is how to solve it");
            question.setTest(test);
            questionRepository.save(question);
        }
    }

    private void createClientGroups() {
        if (clientGroupRepository.count() == 0) {
            Random rnd = new Random();

            ClientGroup grp1 = new ClientGroup();
            grp1.setName("Grp1");
            grp1.setDescription("Test");
            clientGroupRepository.save(grp1);
            ClientGroup grp2 = new ClientGroup();
            grp2.setName("Grp2");
            grp2.setDescription("Test");
            clientGroupRepository.save(grp2);
            ClientGroup grp3 = new ClientGroup();
            grp3.setName("Grp3");
            grp3.setDescription("Test");
            clientGroupRepository.save(grp3);

            for (int i = 0; i < 10; i++) {
                User u = new User();
                u.setUsername("user" + i);
                u.setPassword("test");
                u.setRole(ERole.STUDENT);
                userRepository.save(u);

                Client client = new Client();
                client.setFirstName("fname" + i);
                client.setLastName("lname" + i);
                client.setUser(u);

                client.getGroups().add(grp1);
                if (rnd.nextInt(1000) < 500) {
                    client.getGroups().add(grp2);
                }
                if (rnd.nextInt(1000) < 500) {
                    client.getGroups().add(grp3);
                }
                clientRepository.save(client);
            }
        }

    }
}
