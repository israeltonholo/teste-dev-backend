package com.olisaude.israeltestedevbackend.domain.util;

import com.olisaude.israeltestedevbackend.domain.model.Client;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;

import static com.olisaude.israeltestedevbackend.domain.util.ClockUtils.brazilsDataFormatToParseOffsetDate;

@Component
public class JsonUtils {
    private final ClientUtils clientUtils;

    public JsonUtils(ClientUtils clientUtils) {
        this.clientUtils = clientUtils;
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
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

        return client;
    }
}
