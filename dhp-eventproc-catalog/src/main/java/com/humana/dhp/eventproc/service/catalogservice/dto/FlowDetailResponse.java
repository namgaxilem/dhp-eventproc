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
public class FlowDetailResponse extends FlowResponse{
    private String description;
    private String updatedBy;

    public FlowDetailResponse(FlowEntity flowEntity){
        super(flowEntity.getFlowId(),flowEntity.getFlowName(),flowEntity.getUpdateAt());
        setDescription(flowEntity.getDescription());
        setUpdatedBy(flowEntity.getUpdatedBy());
    }
}
