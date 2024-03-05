package net.atos.beerapi.controller;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.atos.beerapi.model.Beer;
import net.atos.beerapi.provider.JwtTokenProvider;
import net.atos.beerapi.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/beers")
@Tag(name= "Beer", description = "Aba responsável pelo manejo de cervejas no sistema")
@OpenAPIDefinition(
        info = @Info(
                title = "Documentação da API de cervejas",
                description = "API utilizada em treinamento",
                version = "1.0.0",
                contact = @Contact(name = "Hiago Silvério", url = "github.com/silveriohiago", email = "hiago.silverio@atos.net")
        )
)
public class BeerController {

    private final BeerService beerService;
    private final JwtTokenProvider tokenProvider;
    @Autowired
    public BeerController(BeerService beerService, JwtTokenProvider tokenProvider) {
        this.beerService = beerService;
        this.tokenProvider = tokenProvider;
    }


    @GetMapping(produces = "application/json")
    @Operation(
        summary = "Lista todas as cervejas",
        description = "Lista todas as cervejas cadastradas no sistema"
    )

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operação com sucesso")
    })

    public ResponseEntity<String> getAllBeer(@RequestHeader("Authorization") String token) {

        if(token == null || token.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String jwtToken = token.substring(7);
        Claims claims = tokenProvider.validateToken(jwtToken);
        if (claims == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

       return ResponseEntity.ok().build();
       /* List<Beer> beers = beerService.getAllBeers();
        return ResponseEntity.ok(beers);*/

    }

    @GetMapping(value =  "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Lista uma cerveja apenas",
            description = "Lista uma cerveja apenas no sistema!"
    )

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "operação com sucesso")
    })

    public Beer getBeerById(@Parameter(description = "Id da cerveja a ser listada")  @PathVariable Long id) {
        return beerService.getBeerById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)

    @Operation(
            summary = "Insere uma cerveja",
            description = "Insere a cerveja com os dados especificados no sistema"
    )

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "operação com sucesso"),
    })

    public Beer saveBeer(@Parameter(description = "Json contendo os dados da cerveja")  @RequestBody Beer beer) {
        return beerService.saveBeer(beer);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Atualiza uma cerveja",
            description = "Atualiza a cerveja com os dados especificados no sistema"
    )

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "operação com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
    })

    public Beer updateBeer(@Parameter(description = "Json contendo os dados atualizados") @RequestBody Beer beer) {
        return beerService.updateBeer(beer);
    }

    @DeleteMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(
            summary = "Deleta uma cerveja",
            description = "Deleta a cerveja com o ID específico no sistema"
    )

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Operação com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cerveja não encontrada")
    })

    public void deleteBeer(@Parameter(description = "Id da cerveja a ser deletada") @PathVariable Long id) {
        beerService.deleteBeer(id);
    }

}
