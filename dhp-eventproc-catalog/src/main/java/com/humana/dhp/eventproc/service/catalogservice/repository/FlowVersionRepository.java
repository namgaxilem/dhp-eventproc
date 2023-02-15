package com.humana.dhp.eventproc.service.catalogservice.repository;

import com.humana.dhp.eventproc.service.catalogservice.model.FlowVersionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlowVersionRepository extends JpaRepository<FlowVersionEntity, UUID> {
    @Query(value = "SELECT * FROM FLOW_VERSION f WHERE f.flow_id= :flow_id",
            nativeQuery = true)
    Page<FlowVersionEntity> findAllByFlowId(@Param("flow_id") UUID flowId, Pageable pageable);
    @Query(value = "SELECT * FROM FLOW_VERSION f WHERE f.flow_id= :flow_id and f.version = :version", nativeQuery = true)
    FlowVersionEntity findOneByFlowIdAndVersion(@Param("flow_id") UUID flowId, @Param("version") Integer version);
}
