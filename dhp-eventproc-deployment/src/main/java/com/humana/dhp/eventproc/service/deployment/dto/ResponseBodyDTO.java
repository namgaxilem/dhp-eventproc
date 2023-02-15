package com.humana.dhp.eventproc.service.deployment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBodyDTO {
  private String message;
  private String timestamp;
}
