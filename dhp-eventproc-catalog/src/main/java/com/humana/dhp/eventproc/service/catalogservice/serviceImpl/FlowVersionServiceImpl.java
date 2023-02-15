package com.humana.dhp.eventproc.service.catalogservice.serviceImpl;

import com.google.gson.reflect.TypeToken;
import com.humana.dhp.eventproc.service.catalogservice.constant.Constant;
import com.humana.dhp.eventproc.service.catalogservice.constant.ErrorConstant;
import com.humana.dhp.eventproc.service.catalogservice.exeption.BadRequestException;
import com.humana.dhp.eventproc.service.catalogservice.model.FlowEntity;
import com.humana.dhp.eventproc.service.catalogservice.model.FlowVersionEntity;
import com.humana.dhp.eventproc.service.catalogservice.dto.*;
import com.humana.dhp.eventproc.service.catalogservice.repository.FlowRepository;
import com.humana.dhp.eventproc.service.catalogservice.repository.FlowVersionRepository;
import com.humana.dhp.eventproc.service.catalogservice.service.FlowVersionService;
import com.humana.dhp.eventproc.service.catalogservice.utils.GsonUtil;
import com.humana.dhp.eventproc.service.catalogservice.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlowVersionServiceImpl implements FlowVersionService {
    @Autowired
    FlowRepository flowRepository;
    @Autowired
    FlowVersionRepository flowVersionRepository;
    private static int COUNT_VERSION = 1;

    @Override
    public FlowVersionResponse importFlowVersion(UUID flowId, FlowVersionRequest flowVersionRequest) {
        FlowEntity flowEntity = flowRepository.findOneByFlowId(flowId);
        if (flowEntity == null) {
            throw new BadRequestException(String.format(ErrorConstant.FLOW_ID_NOT_EXISTS, flowId));
        }
        Timestamp flowTimestamp = new Timestamp(System.currentTimeMillis());
        flowEntity.setUpdateAt(flowTimestamp);
        FlowVersionEntity flowVersionEntity = new FlowVersionEntity(flowVersionRequest);
        int version = flowEntity.getFlowVersions().size() + COUNT_VERSION;
        flowVersionEntity.setVersion(version);
        flowVersionEntity.setUpdateAt(flowTimestamp);
        flowVersionEntity.setFlow(flowEntity);
        flowEntity.getFlowVersions().add(flowVersionEntity);
        flowEntity.setCountVersion(version);
        flowRepository.save(flowEntity);
        //if
        return new FlowVersionResponse(flowVersionEntity);
    }

    @Override
    public List<FlowVersionResponse> findAllVersion(UUID flowId, int page, int pageSize) {
//        FlowEntity flowEntity = flowRepository.findOneByFlowId(flowId);
//        if (flowEntity == null) {
//            throw new BadRequestException(ErrorConstant.FLOW_NOT_FOUND);
//        }
        Page<FlowVersionEntity> flowVersionEntitiesPage;
        List<FlowVersionEntity> flowVersionEntities;
        flowVersionEntitiesPage = flowVersionRepository.findAllByFlowId(flowId, PageRequest.of(page - 1, pageSize));
        flowVersionEntities = flowVersionEntitiesPage.getContent();
        List<FlowVersionResponse> flowVersionResponses = flowVersionEntities.stream().map(entity ->
                new FlowVersionResponse(entity.getFlow().getFlowId(), null, entity.getVersion(), entity.getComment())
        ).collect(Collectors.toList());
        return flowVersionResponses;
    }

    @Override
    public FlowVersionResponse findOneByFlowIdAndVersion(UUID flowId, Integer version) {
//        FlowEntity flowEntity = flowRepository.findOneByFlowId(flowId);
//        if (flowEntity == null) {
//            return ResponseUtil.getMessage("FLow not found!!!");
//        }
        FlowVersionEntity flowVersionEntity = flowVersionRepository.findOneByFlowIdAndVersion(flowId, version);
        if (flowVersionEntity == null) {
            throw new BadRequestException(ErrorConstant.FLOW_NOT_FOUND);
        }

        FlowVersionResponse flowVersionResponse = new FlowDetailVersionResponse(flowVersionEntity);
        return flowVersionResponse;
    }

    @Override
    public int count() {
        return (int)flowVersionRepository.count();
    }
}
