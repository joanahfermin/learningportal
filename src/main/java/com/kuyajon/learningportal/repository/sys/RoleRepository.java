package com.kuyajon.learningportal.repository.sys;

import com.kuyajon.learningportal.model.sys.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
