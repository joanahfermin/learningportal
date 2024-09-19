package com.kuyajon.learningportal.repository.client;

import com.kuyajon.learningportal.model.client.ClientGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientGroupRepository extends JpaRepository<ClientGroup, Long> {
}
