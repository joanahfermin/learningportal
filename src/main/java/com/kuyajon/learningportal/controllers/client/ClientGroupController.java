package com.kuyajon.learningportal.controllers.client;


import com.kuyajon.learningportal.dto.client.ClientGroupDTO;
import com.kuyajon.learningportal.model.client.ClientGroup;
import com.kuyajon.learningportal.repository.client.ClientGroupRepository;
import com.kuyajon.learningportal.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientgroup")
@CrossOrigin(origins = "*")
public class ClientGroupController {
    @Autowired
    private ClientGroupRepository clientGroupRepository;

    @Autowired
    private ClientService clientService;

    //getAllClientGroup
    //getClientGroupById
    //createClientGroup
    //updateClientGroup
    //deleteClientGroup

    @GetMapping
    public List<ClientGroupDTO> getAllClientGroup(){
        List<ClientGroup> clientGroups = clientService.getAllClientGroup();
        List<ClientGroupDTO> result = new ArrayList<ClientGroupDTO>();

        for (ClientGroup clientGroup : clientGroups){
            ClientGroupDTO dto = convertToDTO(clientGroup);
            result.add(dto);
        }
        return result;
    }

    private ClientGroupDTO convertToDTO(ClientGroup clientGroup){
        ClientGroupDTO clientGroupDTO = new ClientGroupDTO();
        clientGroupDTO.setName(clientGroup.getName());
        clientGroupDTO.setDescription(clientGroup.getDescription());
        return clientGroupDTO;
    }
}
