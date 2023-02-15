package com.humana.dhp.eventproc.service.deployment.config;

import com.humana.dhp.eventproc.service.deployment.DeploymentTimeline;
import com.humana.dhp.eventproc.service.deployment.NifiAgentInfo;
import com.humana.dhp.eventproc.service.deployment.enums.UserRequestType;
import org.javatuples.Pair;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FlowDeploymentConfig {

  private final Map<UUID, Pair<NifiAgentInfo, DeploymentTimeline>> runningDeploymentMap = new HashMap<>();
  private final Map<Long, UUID> runningThreadMap = new HashMap<>();

  public void createFlowDeploymentConfig(UUID deploymentId, Long threadId, UserRequestType userRequestType) {
    runningDeploymentMap.put(deploymentId, new Pair<>(new NifiAgentInfo(),
        new DeploymentTimeline(userRequestType, new ArrayList<>())));
    runningThreadMap.put(threadId, deploymentId);
  }

  public void setNifiAgentInfo(UUID deploymentId, NifiAgentInfo nifiAgentInfo) {
    Pair<NifiAgentInfo, DeploymentTimeline> deploymentTimelinePair = runningDeploymentMap.get(deploymentId);
    deploymentTimelinePair = deploymentTimelinePair.setAt0(nifiAgentInfo);
    runningDeploymentMap.put(deploymentId, deploymentTimelinePair);
  }

  public String getBaseAgentUrl(Long threadId) {
    UUID deploymentId = runningThreadMap.get(threadId);
    return runningDeploymentMap.get(deploymentId).getValue0().getBaseAgentUrl();
  }

  public String getAgentAccessToken(Long threadId) {
    UUID deploymentId = runningThreadMap.get(threadId);
    return runningDeploymentMap.get(deploymentId).getValue0().getAgentAccessToken();
  }

  public void addTimeline(UUID deploymentId, String message) {
    Pair<NifiAgentInfo, DeploymentTimeline> deploymentTimelinePair = runningDeploymentMap.get(deploymentId);
    DeploymentTimeline deploymentTimeline = deploymentTimelinePair.getValue1();
    deploymentTimeline.addTimeline(message);
    deploymentTimelinePair = deploymentTimelinePair.setAt1(deploymentTimeline);
    runningDeploymentMap.put(deploymentId, deploymentTimelinePair);
  }

  public void addTimelineByThreadId(Long threadId, String message) {
    UUID deploymentId = runningThreadMap.get(threadId);
    addTimeline(deploymentId, message);
  }

  public DeploymentTimeline getTimeline(UUID deploymentId) {
    return runningDeploymentMap.get(deploymentId).getValue1();
  }

  public void removeFlowDeploymentConfig(UUID deploymentId, Long threadId) {
    runningDeploymentMap.remove(deploymentId);
    runningThreadMap.remove(threadId);
  }

}
