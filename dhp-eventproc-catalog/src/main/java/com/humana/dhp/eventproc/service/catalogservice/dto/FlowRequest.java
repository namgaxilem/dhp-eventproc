package com.humana.dhp.eventproc.service.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowRequest {
    @NotBlank(message = "FlowName must not be empty")
    private String flowName;
    private String description;
    @Valid
    private FlowVersionRequest dataFlow;
}
