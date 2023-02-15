package com.humana.dhp.eventproc.service.deployment;

import com.humana.dhp.eventproc.service.deployment.dto.ResponseBodyDTO;
import com.humana.dhp.eventproc.service.deployment.enums.UserRequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentTimeline {

  private UserRequestType type;
  private List<ResponseBodyDTO> list = new ArrayList<>();

  public void addTimeline(String message) {
    list.add(new ResponseBodyDTO(message, LocalDateTime.now().toString()));
  }
}
