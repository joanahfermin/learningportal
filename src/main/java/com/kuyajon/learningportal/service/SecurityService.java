package com.kuyajon.learningportal.service;

import com.kuyajon.learningportal.model.course.*;
import com.kuyajon.learningportal.model.sys.ERole;
import com.kuyajon.learningportal.model.sys.User;
import com.kuyajon.learningportal.repository.sys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String username, String password, ERole role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return saveOrUpdateUser(user);
    }
    
    public User getUserByID(Long id){
        return userRepository.findById(id).get();
    }

    public User saveOrUpdateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
