package com.olisaude.israeltestedevbackend.domain.util;

import com.olisaude.israeltestedevbackend.domain.model.Client;
import com.olisaude.israeltestedevbackend.domain.model.HealthIssues;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import static com.olisaude.israeltestedevbackend.domain.util.ClockUtils.brazilsDataFormatToParseOffsetDate;

@Component
public class JsonUtils {
    private final ClientUtils clientUtils;
    private final HealthIssuesUtils healthIssuesUtils;

    public JsonUtils(ClientUtils clientUtils, com.olisaude.israeltestedevbackend.domain.util.HealthIssuesUtils healthIssuesUtils) {
        this.clientUtils = clientUtils;
        this.healthIssuesUtils = healthIssuesUtils;
    }

    public Client convertJsonToClient(JSONObject clientJson) {

        String name;
        String surname;
        String birthDate;
        String gender;

        Client client = new Client();

        try {

            name = (String) clientJson.get("name");
            client.setName(clientUtils.validateName(name));
            surname = (String) clientJson.get("surname");
            client.setSurname(clientUtils.validateName(surname));
            gender = (String) clientJson.get("gender");
            client.setGender(clientUtils.validateGender(gender));
            birthDate = (String) clientJson.get("birthDate");
            clientUtils.validateBirthDate(birthDate);
            client.setBirthDate(brazilsDataFormatToParseOffsetDate(birthDate));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return client;
    }

    public HealthIssues convertJsonToHealthIssues(JSONObject issueJson) {

        String name;
        Integer levelDisease;
        Integer userId;

        HealthIssues healthIssues = new HealthIssues();

        try {

            name = (String) issueJson.get("name");
            healthIssues.setName(healthIssuesUtils.validateIssueName(name));
            levelDisease = (Integer) issueJson.get("levelDisease");
            healthIssues.setLevelDisease(healthIssuesUtils.validateDiseaseLevel(levelDisease));
            userId = (Integer) issueJson.get("userId");
            healthIssuesUtils.validadeFkClientUserId(userId);
            healthIssues.setUserId(Long.valueOf(userId));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return healthIssues;


    }
}
