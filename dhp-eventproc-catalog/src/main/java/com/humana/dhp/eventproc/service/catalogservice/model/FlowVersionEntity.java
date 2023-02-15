package com.humana.dhp.eventproc.service.catalogservice.model;

import javax.persistence.*;

import com.humana.dhp.eventproc.service.catalogservice.dto.FlowVersionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flow_version")
@IdClass(FlowVersionId.class)
public class FlowVersionEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flow_id", nullable = false)
    private FlowEntity flow;
    @Id
    private int version;
    private String comment;
    @Lob
    private byte[] content;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updateAt;
    @Column(name = "updated_by")
    private String updatedBy;

    @Override
    public String toString() {
        return "FlowVersionEntity{" +
                "version=" + version +
                ", comment='" + comment + '\'' +
                ", content='" + content + '\'' +
                ", updateAt=" + updateAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }

    public FlowVersionEntity(FlowVersionRequest versionRequest){
        setComment(versionRequest.getComment());
        setContent(versionRequest.getContent());
    }
}

@AllArgsConstructor
@NoArgsConstructor
class FlowVersionId implements Serializable {
    private FlowEntity flow;
    private int version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowVersionId that = (FlowVersionId) o;
        return version == that.version && Objects.equals(flow, that.flow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flow, version);
    }
}


