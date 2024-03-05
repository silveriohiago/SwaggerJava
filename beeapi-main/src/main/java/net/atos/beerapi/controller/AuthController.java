package net.atos.beerapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.atos.beerapi.provider.JwtTokenProvider;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    public AuthController(JwtTokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }
    @GetMapping(value =  "/generate-token")
    @Operation(
            summary = "Gera um token automático",
            description = "Gerar um token de login"
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação com sucesso")
    })

    @ResponseBody
    public String  generateToken() {
        return tokenProvider.generateToken();
    }

}