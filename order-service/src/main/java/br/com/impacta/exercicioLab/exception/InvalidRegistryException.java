package br.com.impacta.exercicioLab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidRegistryException extends Exception {

    public InvalidRegistryException(String msg) {
        super();
    }

}