package com.humana.dhp.eventproc.service.deployment.controller;

import com.humana.dhp.eventproc.service.deployment.dto.request.ApprovalRequestDTO;
import com.humana.dhp.eventproc.service.deployment.enums.UserRequestState;
import com.humana.dhp.eventproc.service.deployment.service.FlowDeploymentService;
import com.humana.dhp.eventproc.service.deployment.service.UserRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class ApprovalController {

  private final FlowDeploymentService flowDeploymentService;
  private final UserRequestService userRequestService;

  public ApprovalController(FlowDeploymentService flowDeploymentService,
                            UserRequestService userRequestService) {
    this.flowDeploymentService = flowDeploymentService;
    this.userRequestService = userRequestService;
  }

  @PutMapping("/v1/approval")
  public ResponseEntity<UUID> adminApproval(
    @PathVariable UUID userRequestId,
    @RequestBody ApprovalRequestDTO approvalRequestDTO
  ) {
    log.info("Approve request: " + userRequestId);
    log.info("adminApproval thread: " + Thread.currentThread().getName());
    if (approvalRequestDTO.getUserRequestState() == UserRequestState.APPROVED) {
      flowDeploymentService.deployFlowDeployment();
    }
    return new ResponseEntity(UUID.randomUUID(), HttpStatus.OK);
  }
}
