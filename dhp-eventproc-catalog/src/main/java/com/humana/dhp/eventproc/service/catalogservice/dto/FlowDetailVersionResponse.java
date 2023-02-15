package com.humana.dhp.eventproc.service.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.humana.dhp.eventproc.service.catalogservice.model.FlowVersionEntity;
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
public class FlowDetailVersionResponse extends FlowVersionResponse{
    private byte[] content;
    private Timestamp updateAt;
    private String updatedBy;

    public FlowDetailVersionResponse(FlowVersionEntity versionEntity) {
        super(versionEntity.getFlow().getFlowId(), null,versionEntity.getVersion(),versionEntity.getComment());
        setContent(versionEntity.getContent());
        setUpdateAt(versionEntity.getUpdateAt());
        setUpdatedBy(versionEntity.getUpdatedBy());
    }
}
