package com.humana.dhp.eventproc.service.catalogservice.service;


import com.humana.dhp.eventproc.service.catalogservice.dto.APIResponse;
import com.humana.dhp.eventproc.service.catalogservice.dto.FlowRequest;
import com.humana.dhp.eventproc.service.catalogservice.dto.FlowResponse;
import com.humana.dhp.eventproc.service.catalogservice.model.FlowEntity;

import java.util.List;
import java.util.UUID;

public interface FlowService {
    FlowResponse importFlowDefinition(FlowRequest flow);
    FlowResponse findOneByFlowId(UUID flowId);
    List<FlowResponse> findAll(int page, int pageSize);
    int count();
}
