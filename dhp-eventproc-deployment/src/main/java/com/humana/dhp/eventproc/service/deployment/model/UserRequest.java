package com.humana.dhp.eventproc.service.deployment.model;

import com.humana.dhp.eventproc.service.deployment.attributeconverter.CryptoConverter;
import com.humana.dhp.eventproc.service.deployment.enums.UserRequestState;
import com.humana.dhp.eventproc.service.deployment.enums.UserRequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "user_request")
@Table(name = "user_request")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "request_id")
    @Type(type = "uuid-char")
    private UUID requestId;

    @Column(name = "request_state")
    @Enumerated(EnumType.STRING)
    private UserRequestState requestState;

    @Column(name = "request_type")
    @Enumerated(EnumType.STRING)
    private UserRequestType requestType;

    private String requestedBy;
    private String requestedAt;
    private String approvedBy;
    private String approvedAt;
    private UUID deploymentId;

    @Lob
    @Convert(converter = CryptoConverter.class)
    private String data;
}
