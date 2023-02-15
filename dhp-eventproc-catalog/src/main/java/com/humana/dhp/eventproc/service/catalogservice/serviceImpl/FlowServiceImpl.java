package com.humana.dhp.eventproc.service.catalogservice.serviceImpl;

import com.humana.dhp.eventproc.service.catalogservice.constant.Constant;
import com.humana.dhp.eventproc.service.catalogservice.constant.ErrorConstant;
import com.humana.dhp.eventproc.service.catalogservice.exeption.BadRequestException;
import com.humana.dhp.eventproc.service.catalogservice.model.FlowEntity;
import com.humana.dhp.eventproc.service.catalogservice.dto.*;
import com.humana.dhp.eventproc.service.catalogservice.repository.FlowRepository;
import com.humana.dhp.eventproc.service.catalogservice.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    FlowRepository flowRepository;


    @Override
    public FlowResponse importFlowDefinition(FlowRequest flowRequest) {
        FlowEntity flowEntity = flowRepository.findOneByFlowName(flowRequest.getFlowName().trim());
        if (flowEntity != null) {
            throw new BadRequestException(ErrorConstant.DATA_FLOW_EXISTS);
        }
        flowEntity = new FlowEntity(flowRequest);
        flowEntity.setCountVersion(Constant.INIT_VERSION);
        flowEntity = flowRepository.save(flowEntity);
        return new FlowResponse(flowEntity);
    }

    @Override
    public FlowResponse findOneByFlowId(UUID flowId) {
        FlowEntity flowEntity = flowRepository.findOneByFlowId(flowId);
        if (flowEntity == null) {
            throw new BadRequestException(ErrorConstant.FLOW_NOT_FOUND);
        }
        FlowDetailResponse flowDetailResponse = new FlowDetailResponse(flowEntity);
        return flowDetailResponse;
    }

    @Override
    public List<FlowResponse> findAll(int page, int pageSize) {
        Page<FlowEntity> flowEntitiesPage;
        List<FlowEntity> flowEntities;
        flowEntitiesPage = flowRepository.findAll(PageRequest.of(page - 1, pageSize));
        flowEntities = flowEntitiesPage.getContent();
        List<FlowResponse> flowResponses = flowEntities.stream().map(flowEntity ->
            new FlowResponse(flowEntity)
        ).collect(Collectors.toList());
        return flowResponses;
    }

    @Override
    public int count() {
        return (int)flowRepository.count();
    }

}
