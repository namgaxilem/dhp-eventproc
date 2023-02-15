package com.humana.dhp.eventproc.service.deployment.dto.request;

import com.humana.dhp.eventproc.service.deployment.enums.UserRequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApprovalRequestDTO {
  private UserRequestState userRequestState;
}
