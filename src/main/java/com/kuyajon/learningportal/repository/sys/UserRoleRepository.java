package com.kuyajon.learningportal.repository.sys;

import com.kuyajon.learningportal.model.sys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<User, Long> {
}
