package com.olisaude.israeltestedevbackend.domain.util;

import com.olisaude.israeltestedevbackend.domain.model.Client;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public class JsonUtils {

    private final ClockUtils clockUtils;

    public JsonUtils(ClockUtils clockUtils) {
        this.clockUtils = clockUtils;
    }

    public Client convertJsonToClient(JSONObject clientJson) {

        JSONParser parser = new JSONParser(",");
        String name;
        String surname;
        String birthDate;
        String gender;

        Client client = new Client();

        try {
            name = (String) clientJson.get("name");
            client.setName(name);
            surname = (String) clientJson.get("surname");
            client.setSurname(surname);
            gender = (String) clientJson.get("gender");
            client.setGender(gender);
            birthDate = (String) clientJson.get("birthDate");
            client.setBirthDate(clockUtils.brazilsDataFormatToParseOffsetDate(birthDate));
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

        return client;
    }
}
