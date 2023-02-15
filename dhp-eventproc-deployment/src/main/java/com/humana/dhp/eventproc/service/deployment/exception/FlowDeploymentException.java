package com.humana.dhp.eventproc.service.deployment.exception;

public class FlowDeploymentException extends RuntimeException {
  public FlowDeploymentException(Exception e) {
    super(e);
  }

  public FlowDeploymentException(String message) {
    super(message);
  }

}
