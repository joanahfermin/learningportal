package com.kuyajon.learningportal.dto.client;

import lombok.Data;

@Data
public class ClientGroupDTO {
    private Long id;
    private String name;
    private String description;

    private Long clientId;
    private Long groupId;
}
