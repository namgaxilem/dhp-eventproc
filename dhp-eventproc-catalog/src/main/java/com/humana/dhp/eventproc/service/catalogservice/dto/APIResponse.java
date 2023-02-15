package com.humana.dhp.eventproc.service.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {
    private String message;
    private UUID flowId;
    private Integer version;
    private FlowDetailResponse dataFlow;
    private List<FlowResponse> dataFlows;
    private Pagination pagination;
    private List<FlowVersionResponse> dataFlowVersions;
    private FlowDetailVersionResponse dataFlowVersion;

}
