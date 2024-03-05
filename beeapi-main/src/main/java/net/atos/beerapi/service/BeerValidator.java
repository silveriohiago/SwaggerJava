package net.atos.beerapi.service;

import net.atos.beerapi.model.Beer;

import java.util.Arrays;
import java.util.List;

public class BeerValidator {

    private static final List<String> listBeer = Arrays.asList("lager", "pilsen", "malzbier", "puro malte");
    public static void validate(Beer beer) {
        validateId(beer.getId());
        validateName(beer.getName());
        validateType(beer.getType());
        validateAlcoholContent(beer.getAlcoholContent());
    }

    public static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um valor inteiro positivo não nulo");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.length() >= 100) {
            throw new IllegalArgumentException("O nome deve não ser nulo e o valor deve ser abaixo dos 100 caracteres");
        }
    }

    private static void validateType(String type) {
        if (type == null || !listBeer.contains(type.toLowerCase())) {
            throw new IllegalArgumentException("O tipo não pode ser nulo e os valores recebidos precisam ser Lager, Puro Malte, Pilsen ou Malzbier \n Valor Fornecido: " + type);
        }
    }

    private static void validateAlcoholContent(Double alcoholContent) {
        if (alcoholContent <= 0.0 || alcoholContent >= 65.0) {
            throw new IllegalArgumentException("O teor alcoólico deve ser um valor entre 0.0 e 65.0 \n Valor Fornecido: " + alcoholContent);
        }
    }
}


