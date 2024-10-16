package com.kuyajon.learningportal.controllers.client;

import com.kuyajon.learningportal.dto.client.ClientDTO;
import com.kuyajon.learningportal.model.client.Client;
import com.kuyajon.learningportal.model.client.ClientGroup;
import com.kuyajon.learningportal.model.sys.User;
import com.kuyajon.learningportal.repository.client.ClientRepository;
import com.kuyajon.learningportal.service.ClientService;
import com.kuyajon.learningportal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    // getAllClients - done
    // getClientById - done

    // createClient
    // updateClient - done
    // deleteClient - done

    @GetMapping
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> result = new ArrayList<ClientDTO>();

        for (Client client : clients) {
            ClientDTO clientDTO = convertToDTO(client);
            result.add(clientDTO);
        }
        return result;
    }

    @GetMapping("/{id}")
    public Optional<ClientDTO> getClientById(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.getClientByID(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            ClientDTO clientDTO = convertToDTO(client);
            return Optional.of(clientDTO);
        } else {
            throw new IllegalArgumentException("Client ID must not be null");
        }
    }

    @PostMapping
    public ClientDTO createClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        // On create
        // Create a method in ClientService called RegisterClient with parameter
        // ClientDTO
        // Create user based on clientDTO.username
        // Create the Client, you shoud populate client.groups

        Optional<User> optionalClient = clientService.getByUserId(clientDTO.getUserId());
        return null;
    }

    @PutMapping
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Optional<Client> optionalClient = clientService.getClientByID(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setFirstName(clientDTO.getFirstName());
            client.setLastName(clientDTO.getLastName());
            client = clientService.saveOrUpdateClient(client);
            return convertToDTO(client);
        } else {
            throw new IllegalArgumentException("Client ID must not be null");
        }
    }

    @DeleteMapping
    public void deleteClient(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.getClientByID(id);

        if (optionalClient.isPresent()) {
            clientService.deleteClientById(id);
        } else {
            throw new IllegalArgumentException("No client ID found.");
        }
    }

    private ClientDTO convertToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setUserId(client.getUser().getId());

        List<Long> clientGroupIds = new ArrayList<Long>(client.getGroups().size());
        for (ClientGroup grp : client.getGroups()) {
            clientGroupIds.add(grp.getId());
        }
        clientDTO.setClientGroupIds(clientGroupIds);
        return clientDTO;
    }
}
