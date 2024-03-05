package net.atos.beerapi.exception;

import com.fasterxml.jackson.core.JsonParseException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.atos.beerapi.exception.BeerNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;


@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
    @ExceptionHandler(BeerNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Cerveja não encontrada", content = @Content)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleBeerNotFound(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyBeerListException.class)
    @ApiResponse(responseCode = "404", description = "Não existem cervejas cadastradas no momento", content = @Content)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEmptyBeerList(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ApiResponse(responseCode = "500", description = "Erro Interno", content = @Content)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleRuntimeException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("[Internal server error] " + e.getMessage());
    }
}
