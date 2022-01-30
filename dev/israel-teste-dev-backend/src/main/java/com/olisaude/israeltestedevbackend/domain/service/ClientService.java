package com.olisaude.israeltestedevbackend.domain.service;

import com.olisaude.israeltestedevbackend.domain.exceptions.ClientNotFoundException;
import com.olisaude.israeltestedevbackend.domain.model.Client;
import com.olisaude.israeltestedevbackend.domain.repository.ClientRepository;
import com.olisaude.israeltestedevbackend.domain.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ClientService {

    private final JsonUtils jsonUtils;

    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(JsonUtils jsonUtils, ClientRepository clientRepository) {
        this.jsonUtils = jsonUtils;
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        try {
            List<Client> clientList = clientRepository.findAll();
            return clientList;
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
    }

    public Client setClient(String newClient) {

        Client client = new Client();
        try {
            JSONObject jsonObject = new JSONObject(newClient);;
            client = jsonUtils.convertJsonToClient(jsonObject);
            client.setLastUpdate(OffsetDateTime.now());
            client.setRegistrationDate(OffsetDateTime.now());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        clientRepository.save(client);
        return client;
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException());
    }
 }
