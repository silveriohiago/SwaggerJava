package net.atos.beerapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Id é necessário")
    @Schema(description = "Id do produto", example = "1")
    private Long id;

    @Size(min = 0, max = 100)
    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome da cerveja", example = "Budweiser Long Neck 330ml")
    private String name;

    @NotBlank(message = "Tipo da cerveja é obrigatório")
    @Schema(description = "Tipo da cerveja",
            allowableValues = {"Lager", "Puro Malte", "Pilsen", "Malzbier"},
            example = "Lager")
    private String type;

    @Schema(description = "Teor Alcoólico", example = "5.0", minimum = "0.0", maximum = "65.0")

    private double alcoholContent;

}
