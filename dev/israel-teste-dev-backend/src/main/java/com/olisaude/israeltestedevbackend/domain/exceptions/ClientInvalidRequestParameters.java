package com.olisaude.israeltestedevbackend.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientInvalidRequestParameters extends RuntimeException{
}
