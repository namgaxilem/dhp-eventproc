package com.humana.dhp.eventproc.service.catalogservice.model;

import com.humana.dhp.eventproc.service.catalogservice.constant.Constant;
import com.humana.dhp.eventproc.service.catalogservice.dto.FlowRequest;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.convert.WritingConverter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;


@Entity
@Table(name = "flow")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FlowEntity {
    @Id
    @GeneratedValue
    @Column(name = "flow_id")
    private UUID flowId;
    @Column(name = "flow_name")
    private String flowName;
    private String description;
    @Column(name = "count_version")
    private int countVersion;
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updateAt;
    @Column(name = "updated_by")
    private String updatedBy;

    @OneToMany(mappedBy = "flow", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FlowVersionEntity> flowVersions = new HashSet<>();

    public FlowEntity(FlowRequest flowRequest) {
        FlowVersionEntity versionEntity = new FlowVersionEntity(flowRequest.getDataFlow());
        versionEntity.setVersion(Constant.INIT_VERSION);
        versionEntity.setComment(StringUtils.isBlank(versionEntity.getComment()) ? Constant.INIT_COMMENT_VERSION : versionEntity.getComment());
        versionEntity.setFlow(this);
        Set<FlowVersionEntity> versionEntities = new HashSet<>();
        versionEntities.add(versionEntity);
        setFlowName(flowRequest.getFlowName());
        setDescription(StringUtils.isBlank(flowRequest.getDescription()) ? null : flowRequest.getDescription());
        setFlowVersions(versionEntities);
    }

}




