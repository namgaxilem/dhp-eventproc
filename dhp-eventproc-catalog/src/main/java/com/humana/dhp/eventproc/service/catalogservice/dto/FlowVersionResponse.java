package com.humana.dhp.eventproc.service.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.humana.dhp.eventproc.service.catalogservice.model.FlowVersionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowVersionResponse {
    private UUID flowId;
    private String flowName;
    private int version;
    private String comment;

    public FlowVersionResponse(FlowVersionEntity versionEntity){
        setFlowId(versionEntity.getFlow().getFlowId());
        setFlowName(versionEntity.getFlow().getFlowName());
        setVersion(versionEntity.getVersion());
    }
}




