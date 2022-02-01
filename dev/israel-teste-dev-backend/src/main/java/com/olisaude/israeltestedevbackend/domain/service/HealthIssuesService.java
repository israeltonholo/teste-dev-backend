package com.olisaude.israeltestedevbackend.domain.service;

import com.olisaude.israeltestedevbackend.domain.exceptions.ClientNotFoundException;
import com.olisaude.israeltestedevbackend.domain.model.HealthIssues;
import com.olisaude.israeltestedevbackend.domain.repository.HealthIssuesRepository;
import com.olisaude.israeltestedevbackend.domain.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HealthIssuesService {

    private final JsonUtils jsonUtils;

    @Autowired
    private final HealthIssuesRepository healthIssuesRepository;

    public HealthIssuesService(JsonUtils jsonUtils, HealthIssuesRepository healthIssuesRepository) {
        this.jsonUtils = jsonUtils;
        this.healthIssuesRepository = healthIssuesRepository;
    }

    public List<HealthIssues> getAllIssues() {
        try {
            return healthIssuesRepository.findAll();
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
    }

    public List<HealthIssues> getAllIssuesByUserId(Long userId) {
        try {
            return healthIssuesRepository.findByUserId(userId);
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
    }

    @Transactional
    public ResponseEntity<HealthIssues> setIssue(String newIssue) {

        HealthIssues healthIssues = new HealthIssues();
        try {
            JSONObject jsonObject = new JSONObject(newIssue);
            healthIssues = jsonUtils.convertJsonToHealthIssues(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        healthIssuesRepository.save(healthIssues);

        return ResponseEntity.status(HttpStatus.CREATED).body(healthIssues);
    }

    public HealthIssues getIssueById(Long id) {
        return healthIssuesRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    @Transactional
    public HealthIssues deleteIssueById(Long id) {
        try {
            HealthIssues healthIssues = this.getIssueById(id);
            healthIssuesRepository.deleteById(id);
            return healthIssues;
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
    }

    @Transactional
    public ResponseEntity<HealthIssues> putIssueById(Long id, String editedIssue) {

        HealthIssues healthIssues;
        try {
            healthIssues = this.getIssueById(id);
        } catch (RuntimeException exception) {
            throw new ClientNotFoundException();
        }
        try {

            JSONObject jsonObject = new JSONObject(editedIssue);
            HealthIssues newHealthIssues = jsonUtils.convertJsonToHealthIssues(jsonObject);
            healthIssues.setLevelDisease(newHealthIssues.getLevelDisease());
            healthIssues.setName(newHealthIssues.getName());
            healthIssues.setUserId(newHealthIssues.getUserId());
            healthIssuesRepository.save(healthIssues);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(healthIssues);
    }
 }
