package net.atos.beerapi.service;

import com.fasterxml.jackson.core.JsonParseException;
import net.atos.beerapi.exception.BeerNotFoundException;
import net.atos.beerapi.exception.EmptyBeerListException;
import net.atos.beerapi.model.Beer;
import net.atos.beerapi.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.crypto.ExemptionMechanismException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BeerService implements IBeerService {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<Beer> getAllBeers() {
        List<Beer> beer = (List<Beer>) this.beerRepository.findAll();

        if(beer.isEmpty()){
            throw new EmptyBeerListException("Não existem cervejas cadastradas no momento");
        }

        return (List<Beer>) this.beerRepository.findAll();
    }



    @Override
    public Beer getBeerById(Long id) {
        Optional<Beer> optionalBeer = this.beerRepository.findById(id);

        // Se a cerveja não for encontrada, lança a exceção BeerNotFoundException
        if (optionalBeer.isEmpty()) {
            throw new BeerNotFoundException("Cerveja não encontrada com o ID: " + id);
        }

        // Se a cerveja for encontrada, retorna a instância de Beer
        return optionalBeer.get();
    }

    public Beer saveBeer(Beer beer) {
        BeerValidator.validate(beer);
        return this.beerRepository.save(beer);
    }

    @Override
    public Beer updateBeer(Beer beer) {
        Optional<Beer> optionalBeer = beerRepository.findById(beer.getId());
        BeerValidator.validate(beer);

        if (optionalBeer.isPresent()) {
            // Get the existing beer from the Optional
            Beer existingBeer = optionalBeer.get();

            // Update the relevant fields with the values provided
            existingBeer.setName(beer.getName());
            existingBeer.setType(beer.getType());
            existingBeer.setAlcoholContent(beer.getAlcoholContent());

            // Save the updated beer to the database
            return beerRepository.save(existingBeer);

        } else {
            throw new BeerNotFoundException("Não foi possível atualizar, cerveja não foi encontrada com o ID: " + beer.getId());
        }
    }

    @Override
    public void deleteBeer(Long id) {
        Optional<Beer> optionalBeer = this.beerRepository.findById(id);
        BeerValidator.validateId(id);

        // Se a cerveja não for encontrada, lança a exceção BeerNotFoundException
        if (optionalBeer.isEmpty()) {
            throw new BeerNotFoundException("Não foi possível deletar, a cerveja não foi encontrada com o ID: " + id);
        }

        this.beerRepository.deleteById(id);
    }
}
