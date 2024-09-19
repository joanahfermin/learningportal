package com.kuyajon.learningportal.repository.client;

import com.kuyajon.learningportal.model.client.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
}
