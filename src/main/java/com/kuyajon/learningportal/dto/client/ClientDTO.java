package com.kuyajon.learningportal.dto.client;

import java.util.List;
import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long userId;

    private List<Long> clientGroupIds;
}
