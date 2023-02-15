package com.humana.dhp.eventproc.service.deployment.service.impl;

import com.humana.dhp.eventproc.service.deployment.service.AsyncService;
import com.humana.dhp.eventproc.service.deployment.service.FlowDeploymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlowDeploymentServiceImpl implements FlowDeploymentService {

  private AsyncService asyncService;

  @Autowired
  public FlowDeploymentServiceImpl(AsyncService asyncService) {
    this.asyncService = asyncService;
  }

  @Override
  public void createFlowDeployment() {

  }

  @Override
  public void deployFlowDeployment() {
    asyncService.run(this::createFlowDeployment);
  }
}
