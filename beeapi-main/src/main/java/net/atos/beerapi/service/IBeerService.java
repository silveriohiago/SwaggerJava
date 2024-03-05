package net.atos.beerapi.service;

import net.atos.beerapi.model.Beer;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;
import java.util.List;

public interface IBeerService {

    List<Beer> getAllBeers();

    Beer getBeerById(Long id);

    Beer saveBeer(Beer beer);

    Beer updateBeer(Beer beer);

    void deleteBeer(Long id);
}
