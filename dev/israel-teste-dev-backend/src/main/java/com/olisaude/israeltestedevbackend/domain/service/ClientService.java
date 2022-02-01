package com.olisaude.israeltestedevbackend.domain.service;

import com.olisaude.israeltestedevbackend.api.model.ClientDTO;
import com.olisaude.israeltestedevbackend.domain.exceptions.ClientNotFoundException;
import com.olisaude.israeltestedevbackend.domain.model.Client;
import com.olisaude.israeltestedevbackend.domain.repository.ClientRepository;
import com.olisaude.israeltestedevbackend.domain.util.HealthIssuesUtils;
import com.olisaude.israeltestedevbackend.domain.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class ClientService {

    private final JsonUtils jsonUtils;

    @Autowired
    private final ClientRepository clientRepository;
    private final HealthIssuesService healthIssuesService;
    private final HealthIssuesUtils healthIssuesUtils;

    public ClientService(JsonUtils jsonUtils, ClientRepository clientRepository, HealthIssuesService healthIssuesService, HealthIssuesUtils healthIssuesUtils) {
        this.jsonUtils = jsonUtils;
        this.clientRepository = clientRepository;
        this.healthIssuesService = healthIssuesService;
        this.healthIssuesUtils = healthIssuesUtils;
    }

    public List<Client> getAllClients() {
        try {
            return clientRepository.findAll();
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
    }

    @Transactional
    public ResponseEntity<Client> setClient(String newClient) {

        Client client = new Client();
        try {
            JSONObject jsonObject = new JSONObject(newClient);
            client = jsonUtils.convertJsonToClient(jsonObject);
            client.setLastUpdate(OffsetDateTime.now());
            client.setRegistrationDate(OffsetDateTime.now());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    @Transactional
    public Client deleteClientById(Long id) {
        try {
            Client client = this.getClientById(id);
            clientRepository.deleteById(id);
            return client;
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
    }

    @Transactional
    public ResponseEntity<Client> putClientById(Long id, String editedClient) {

        Client client;
        try {
            client = this.getClientById(id);
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
        try {
            JSONObject jsonObject = new JSONObject(editedClient);
            Client newClient = jsonUtils.convertJsonToClient(jsonObject);
            client.setBirthDate(newClient.getBirthDate());
            client.setName(newClient.getName());
            client.setSurname(newClient.getSurname());
            client.setGender(newClient.getGender());
            client.setLastUpdate(OffsetDateTime.now());
            clientRepository.save(client);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    public List<ClientDTO> getTopTenClients() {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        List<Client> allClients = clientRepository.findAll();
        List<Integer> healthIssueLevel = new ArrayList<>();
        for (Client client : allClients) {
            healthIssueLevel.add(healthIssuesService
                    .getAllIssuesByUserId(client.getId())
                    .stream()
                    .reduce(0, (subTotal, healthIssues) -> subTotal + Math.toIntExact(healthIssues.getLevelDisease()), Integer::sum));
        }

        for (int index = 1 ; index < allClients.size(); index += 1) {
            ClientDTO newClientToDTOList = new ClientDTO();
            Client clientFromList = allClients.get(index);
            newClientToDTOList.setId(clientFromList.getId());
            newClientToDTOList.setName(clientFromList.getName());
            newClientToDTOList.setSurname(clientFromList.getSurname());
            newClientToDTOList.setScore((float) healthIssuesUtils.calculateScore(healthIssueLevel.get(index)));

            clientDTOS.add(newClientToDTOList);
        }

        Collections.sort(clientDTOS, new Comparator<ClientDTO>() {
            @Override
            public int compare(ClientDTO o1, ClientDTO o2) {
                return o2.getScore().compareTo(o1.getScore());
            }
        });

        return clientDTOS.subList(0, 10);
    }

 }
