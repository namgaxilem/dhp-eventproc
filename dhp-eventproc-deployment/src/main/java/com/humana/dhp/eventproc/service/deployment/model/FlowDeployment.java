package com.humana.dhp.eventproc.service.deployment.model;

import com.humana.dhp.eventproc.service.deployment.attributeconverter.CryptoConverter;
import com.humana.dhp.eventproc.service.deployment.enums.DeploymentState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "flow_deployment")
@Table(name = "flow_deployment")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FlowDeployment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "deployment_id")
    @Type(type = "uuid-char")
    private UUID deploymentId;

    @Column(name = "bucket_id")
    @Type(type = "uuid-char")
    private UUID bucketId;

    @Column(name = "flow_id")
    @Type(type = "uuid-char")
    private UUID flowId;

    @Column(name = "version")
    private Integer version;

    @Column(name = "environment_id")
    @Type(type = "uuid-char")
    private UUID environmentId;

    @Column(name = "deployment_name")
    private String deploymentName;

    @Column(name = "last_deployed_at")
    private Timestamp lastDeployedAt;

    @Column(name = "last_deployed_by")
    private String lastDeployedBy;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private DeploymentState state;

    @Column(name = "timline")
    private byte[] timeline;

    @Column(name = "registry_bucket_id")
    @Type(type = "uuid-char")
    private UUID registryBucketId;

    @Column(name = "registry_flow_id")
    @Type(type = "uuid-char")
    private UUID registryFlowId;

    @Column(name = "registry_version")
    private Long registryVersion;

    @Column(name = "process_group_id")
    @Type(type = "uuid-char")
    private UUID processGroupId;

    @Column(name = "last_request_id")
    @Type(type = "uuid-char")
    private UUID lastRequestId;

    @Lob
    @Column(name = "parameters")
    @Convert(converter = CryptoConverter.class)
    private byte[] parameters;
}
