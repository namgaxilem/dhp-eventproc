package com.humana.dhp.eventproc.service.catalogservice.controller;

import com.humana.dhp.eventproc.service.catalogservice.constant.ErrorConstant;
import com.humana.dhp.eventproc.service.catalogservice.constant.MessageConstant;
import com.humana.dhp.eventproc.service.catalogservice.dto.*;
import com.humana.dhp.eventproc.service.catalogservice.exeption.BadRequestException;
import com.humana.dhp.eventproc.service.catalogservice.service.FlowService;
import com.humana.dhp.eventproc.service.catalogservice.service.FlowVersionService;
import com.humana.dhp.eventproc.service.catalogservice.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CatalogController extends ExceptionController {
    @Autowired
    FlowService flowService;
    @Autowired
    FlowVersionService flowVersionService;

    @PostMapping("/v1/flows")
    public ResponseEntity<Object> importFlow(@Valid @RequestBody FlowRequest dataFlow) {
        FlowResponse flowResponse = flowService.importFlowDefinition(dataFlow);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setFlowId(flowResponse.getFlowId());
        apiResponse.setMessage(MessageConstant.IMPORT_DATA_FLOW_SUCCESS);
        return ResponseUtil.success(apiResponse);
    }

    @GetMapping("/v1/flows")
//    @PreAuthorize("hasRole('Doc.Test')")
//    @PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasAuthority('SCOPE_Obo.Catalog')")
    public ResponseEntity<Object> getFlows(@RequestParam(required = false) Optional<String> page, @RequestParam(required = false) Optional<String> pageSize) {
        try {
//            String pageNum = page.orElse("0");
//            String pageSizeNum = pageSize.orElse("0");
            int pageNumber = Integer.parseInt(page.get());
            int pageSizeNumber = Integer.parseInt(pageSize.get());
            if (pageNumber <= 0 || pageSizeNumber <= 0) {
                throw new BadRequestException(ErrorConstant.PAGE_OR_PAGE_SIZE_INVALID);
            }
            List<FlowResponse> flowResponses = flowService.findAll(pageNumber, pageSizeNumber);
            Pagination pagination = new Pagination(pageNumber,pageSizeNumber,flowService.count());
            APIResponse apiResponse = new APIResponse();
            apiResponse.setPagination(pagination);
            apiResponse.setDataFlows(flowResponses);
            return ResponseUtil.success(apiResponse);
        } catch (Exception ex) {
            throw new BadRequestException(ErrorConstant.PAGE_OR_PAGE_SIZE_INVALID);
        }
    }

    @GetMapping("/flows/{flowId}")
    public ResponseEntity<Object> getFlow(@PathVariable UUID flowId) {
        FlowResponse flowResponse = flowService.findOneByFlowId(flowId);
        return ResponseUtil.success(flowResponse);
    }

    @PostMapping("/flow-versions/{flowId}")
    public ResponseEntity<Object> importFlowVersion(@RequestBody FlowVersionRequest flowVersion, @PathVariable UUID flowId) {
        FlowVersionResponse flowVersionResponse = flowVersionService.importFlowVersion(flowId, flowVersion);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setFlowId(flowVersionResponse.getFlowId());
        apiResponse.setVersion(flowVersionResponse.getVersion());
        apiResponse.setMessage(String.format(MessageConstant.IMPORT_DATA_FLOW_VERSION_SUCCESS,flowVersionResponse.getFlowName()));
        return ResponseUtil.success(apiResponse);
    }

    @GetMapping("/flow-versions/{flowId}")
    public ResponseEntity<Object> getFlowVersions(@PathVariable UUID flowId, @RequestParam(required = false) Optional<String> page, @RequestParam(required = false) Optional<String> pageSize) {
        try {
//            String pageNum = page.orElse("0");
//            String pageSizeNum = pageSize.orElse("0");
            int pageNumber = Integer.parseInt(page.get());
            int pageSizeNumber = Integer.parseInt(pageSize.get());
            if (pageNumber <= 0 || pageSizeNumber <= 0) {
                throw new BadRequestException(ErrorConstant.PAGE_OR_PAGE_SIZE_INVALID);
            }
            List<FlowVersionResponse> flowVersionResponses = flowVersionService.findAllVersion(flowId, pageNumber, pageSizeNumber);
            Pagination pagination = new Pagination(pageNumber,pageSizeNumber,flowVersionService.count());
            APIResponse apiResponse = new APIResponse();
            apiResponse.setPagination(pagination);
            apiResponse.setDataFlowVersions(flowVersionResponses);
            return ResponseUtil.success(apiResponse);
        } catch (Exception ex) {
            throw new BadRequestException(ErrorConstant.PAGE_OR_PAGE_SIZE_INVALID);
        }
    }

    @GetMapping("/flow-versions/{flowId}/{version}")
    public ResponseEntity<Object> getFlowVersion(@PathVariable UUID flowId, @PathVariable Integer version){
        FlowVersionResponse flowVersionResponse = flowVersionService.findOneByFlowIdAndVersion(flowId,version);
        return ResponseUtil.success(flowVersionResponse);
    }
}
