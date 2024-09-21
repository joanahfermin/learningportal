package com.kuyajon.learningportal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kuyajon.learningportal.repository.course.CourseRepository;
import com.kuyajon.learningportal.model.sys.ERole;
import com.kuyajon.learningportal.model.sys.User;
import com.kuyajon.learningportal.repository.sys.UserRepository;
import com.kuyajon.learningportal.service.SecurityService;

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

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabase() {
        if ("dev".equals(activeProfile)) {
            createInitialDataForDev();
        } else if ("prod".equals(activeProfile)) {
            createInitialDataForProd();
        }
    }

    private void createInitialDataForDev() {
        LOG.info("Creating Initial Data for DEV");
        createDevUsers();
        LOG.info("Creating Initial Data for DEV - Done");
    }

    private void createInitialDataForProd() {
        LOG.info("Creating Initial Data for PROD");
        LOG.info("Creating Initial Data for PROD - Done");
    }

    private void createDevUsers() {
        if (userRepository.count()==0) {
            securityService.createUser("admin", "admin", ERole.ADMIN);
            securityService.createUser("stu", "stu", ERole.STUDENT);
        }
    }

}
