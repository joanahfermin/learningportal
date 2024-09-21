package com.kuyajon.learningportal.repository.sys;

import com.kuyajon.learningportal.model.sys.User;
import com.kuyajon.learningportal.model.sys.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
