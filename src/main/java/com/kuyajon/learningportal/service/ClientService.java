package com.kuyajon.learningportal.service;

import com.kuyajon.learningportal.model.client.*;
import com.kuyajon.learningportal.model.sys.User;
import com.kuyajon.learningportal.repository.client.*;
import com.kuyajon.learningportal.repository.sys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientGroupRepository clientGroupRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private LessonProgressRepository lessonProgressRepository;

    @Autowired
    private TopicProgressRepository topicProgressRepository;



    //Retrieve client by id.
    public Optional<Client> getClientByID(Long id){
        return Optional.of(clientRepository.findById(id).get());
    }

    public Optional<User> getByUserId(Long id){
        return Optional.of(userRepository.findById(id).get());
    }

    public ClientGroup getClientGroupByID(Long id){
        return clientGroupRepository.findById(id).get();
    }

    public Enrollment getEnrollmentByID(Long id){
        return enrollmentRepository.findById(id).get();
    }

    public LessonProgress getLessonProgressByID(Long id){
        return lessonProgressRepository.findById(id).get();
    }

    public TopicProgress getTopicProgressByID(Long id){
        return topicProgressRepository.findById(id).get();
    }



    //Save or update client obj.
    public Client saveOrUpdateClient(Client client){
        return clientRepository.save(client);
    }

    public ClientGroup saveOrUpdateClientGroup(ClientGroup clientGroup){
        return clientGroupRepository.save(clientGroup);
    }

    public Enrollment saveOrUpdateEnrollment(Enrollment enrollment){
        return enrollmentRepository.save(enrollment);
    }

    public LessonProgress saveOrUpdateLessonProgress(LessonProgress enrollment){
        return lessonProgressRepository.save(enrollment);
    }

    public TopicProgress saveOrUpdateTopicProgress(TopicProgress topicProgress){
        return topicProgressRepository.save(topicProgress);
    }



    //Delete client obj.
    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }

    public void deleteClientGroupById(Long id){
        clientGroupRepository.deleteById(id);
    }

    public void deleteEnrollmentById(Long id){
        enrollmentRepository.deleteById(id);
    }

    public void deleteLessonProgressById(Long id){
        lessonProgressRepository.deleteById(id);
    }

    public void deleteTopicProgressById(Long id){
        topicProgressRepository.deleteById(id);
    }


    public List<Client> getClientByUserId(Long userId){
        return clientRepository.findByUserId(userId);
    }
}
