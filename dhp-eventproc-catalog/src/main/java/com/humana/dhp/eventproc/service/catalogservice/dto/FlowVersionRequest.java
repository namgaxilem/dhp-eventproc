package com.humana.dhp.eventproc.service.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlowVersionRequest {
    private String comment;
    @NotBlank(message = "Content must not be empty")
    private byte[] content;
}
