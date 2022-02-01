package com.olisaude.israeltestedevbackend.api.controller;

import com.olisaude.israeltestedevbackend.api.model.ClientDTO;
import com.olisaude.israeltestedevbackend.domain.model.Client;
import com.olisaude.israeltestedevbackend.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClientController {

    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/todos")
    public List<Client> listAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Client> saveClient(@RequestBody String newClient) {
        return clientService.setClient(newClient);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/{id}")
    public Client deleteClient(@PathVariable Long id) {
        return clientService.deleteClientById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Client> putClient(@RequestBody String editedClient, @PathVariable Long id) {
        return clientService.putClientById(id, editedClient);
    }

    @GetMapping("/maior-risco")
    public List<ClientDTO> getTopTenClients() {
        return clientService.getTopTenClients();
    }

}
