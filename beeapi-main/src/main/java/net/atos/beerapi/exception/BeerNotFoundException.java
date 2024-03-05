package net.atos.beerapi.exception;

import net.atos.beerapi.model.Beer;

public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException(String message) {
        super(message);
    }
}