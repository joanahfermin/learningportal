package com.kuyajon.learningportal.service;

import com.kuyajon.learningportal.model.course.*;
import com.kuyajon.learningportal.model.sys.Role;
import com.kuyajon.learningportal.model.sys.User;
import com.kuyajon.learningportal.model.sys.UserRole;
import com.kuyajon.learningportal.repository.sys.RoleRepository;
import com.kuyajon.learningportal.repository.sys.UserRepository;
import com.kuyajon.learningportal.repository.sys.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    
    //Retrieve sys by id.
    public Role getRoleByID(Long id){
        return roleRepository.findById(id).get();
    }

    public User getUserByID(Long id){
        return userRepository.findById(id).get();
    }

    public UserRole getUserRoleID(Long id){
        return userRoleRepository.findById(id).get();
    }



    //Save or update sys obj.
    public Role saveOrUpdateRole(Role role){
        return roleRepository.save(role);
    }

    public User saveOrUpdateUser(User user){
        return userRepository.save(user);
    }

    public UserRole saveOrUpdateUserRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }



    //Delete sys obj.
    public void deleteRoleById(Long id){
        roleRepository.deleteById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void deleteUserRoleById(Long id){
        userRoleRepository.deleteById(id);
    }
}
