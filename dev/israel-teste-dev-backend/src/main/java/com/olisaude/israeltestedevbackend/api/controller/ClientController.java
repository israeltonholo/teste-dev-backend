package com.olisaude.israeltestedevbackend.api.controller;

import com.olisaude.israeltestedevbackend.domain.model.Client;
import com.olisaude.israeltestedevbackend.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

}