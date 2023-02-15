package com.humana.dhp.eventproc.service.deployment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NifiAgentInfo {
  private String baseAgentUrl;
  private String agentAccessToken;
}
