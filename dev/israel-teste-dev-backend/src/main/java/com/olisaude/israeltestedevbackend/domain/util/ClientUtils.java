package com.olisaude.israeltestedevbackend.domain.util;

import com.olisaude.israeltestedevbackend.domain.exceptions.ClientInvalidRequestParameters;
import org.springframework.stereotype.Component;

import static com.olisaude.israeltestedevbackend.domain.util.ClockUtils.isDateValid;


@Component
class ClientUtils {

    public String validateName(String name) {
        if (name.length() < 2) {
            this.clientInvalidRequestParameters();
        }
        return name;
    }

    public String validateGender(String gender) {
        if (!gender.equals("masculino") && !gender.equals("feminino")) {
            this.clientInvalidRequestParameters();
        }
        return gender;
    }

    public void validateBirthDate(String birthDate) {
        if (Boolean.FALSE.equals(isDateValid(birthDate))) {
            this.clientInvalidRequestParameters();
        }
    }

    private RuntimeException clientInvalidRequestParameters() {
        throw new ClientInvalidRequestParameters();
    }
}
