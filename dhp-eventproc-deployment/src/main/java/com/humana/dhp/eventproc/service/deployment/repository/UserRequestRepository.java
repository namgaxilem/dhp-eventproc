package com.humana.dhp.eventproc.service.deployment.repository;

import com.humana.dhp.eventproc.service.deployment.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
}
