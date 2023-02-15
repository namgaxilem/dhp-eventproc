package com.humana.dhp.eventproc.service.deployment.controller;

import com.humana.dhp.eventproc.service.deployment.dto.UserRequestDTO;
import com.humana.dhp.eventproc.service.deployment.model.UserRequest;
import com.humana.dhp.eventproc.service.deployment.repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@Controller
public class UserRequestController {

    @Autowired
    private UserRequestRepository userRequestRepository;

    @PostMapping("/v1/user-request")
    @ResponseBody
    public String createUserRequest(@RequestBody UserRequestDTO userRequestDTO) {
        UserRequest userRequest = new UserRequest();
        userRequest.setData(userRequestDTO.getData());
        UserRequest listCustomers = userRequestRepository.save(userRequest);
        return listCustomers.toString();
    }

    @GetMapping("/v1/user-request")
    @ResponseBody
    public String getUserRequest(@RequestParam Long id) throws SQLException {
        Optional<UserRequest> list = userRequestRepository.findById(id);
        return list.get().getData();
    }
}
