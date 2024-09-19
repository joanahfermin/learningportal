package com.kuyajon.learningportal.repository.sys;

import com.kuyajon.learningportal.model.sys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
