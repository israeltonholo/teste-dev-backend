package com.olisaude.israeltestedevbackend.domain.util;

import com.olisaude.israeltestedevbackend.domain.exceptions.ClientInvalidRequestParameters;
import com.olisaude.israeltestedevbackend.domain.exceptions.ClientNotFoundException;
import com.olisaude.israeltestedevbackend.domain.repository.ClientRepository;
import org.springframework.stereotype.Component;



@Component
public
class HealthIssuesUtils {

    private final ClientRepository clientRepository;


    HealthIssuesUtils(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String validateIssueName(String name) {
        if (name.length() < 2) {
            this.clientInvalidRequestParameters();
        }
        return name;
    }

    public Long validateDiseaseLevel(Integer diseaseLevel) {
        if (!diseaseLevel.equals(1) && !diseaseLevel.equals(2)) {
            this.clientInvalidRequestParameters();
        }
        return Long.valueOf(diseaseLevel);
    }

    public void validadeFkClientUserId(Integer fkClientUserId) {
        clientRepository.findById(Long.valueOf(fkClientUserId)).orElseThrow(ClientNotFoundException::new);
    }

    private RuntimeException clientInvalidRequestParameters() {
        throw new ClientInvalidRequestParameters();
    }

    public double calculateScore(Integer sd) {
        return ( 1 / (1 + Math.pow(Math.E, -(sd - 2.8)))) * 100;
    }
}
