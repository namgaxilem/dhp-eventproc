package com.humana.dhp.eventproc.service.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.humana.dhp.eventproc.service.catalogservice.model.FlowEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowResponse {
    private UUID flowId;
    private String flowName;
    private Timestamp updateAt;
    private Integer countVersion;

    public FlowResponse(UUID flowId, String flowName, Timestamp updateAt) {
        this.flowId = flowId;
        this.flowName = flowName;
        this.updateAt = updateAt;
    }

    public FlowResponse(FlowEntity flowEntity){
        setFlowId(flowEntity.getFlowId());
        setFlowName(flowEntity.getFlowName());
        setCountVersion(flowEntity.getCountVersion());
        setUpdateAt(flowEntity.getUpdateAt());
    }
}




