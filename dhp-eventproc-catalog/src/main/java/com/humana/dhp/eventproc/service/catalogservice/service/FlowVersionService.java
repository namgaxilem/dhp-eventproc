package com.humana.dhp.eventproc.service.catalogservice.service;

import com.humana.dhp.eventproc.service.catalogservice.dto.APIResponse;
import com.humana.dhp.eventproc.service.catalogservice.dto.FlowVersionRequest;
import com.humana.dhp.eventproc.service.catalogservice.dto.FlowVersionResponse;

import java.util.List;
import java.util.UUID;

public interface FlowVersionService {
    FlowVersionResponse importFlowVersion(UUID flowId, FlowVersionRequest flowVersionRequest);
    List<FlowVersionResponse> findAllVersion(UUID flowId, int page, int pageSize);
    FlowVersionResponse findOneByFlowIdAndVersion(UUID flowId, Integer version);
    int count();
}
