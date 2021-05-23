package cl.com.nisum.ws.technicalexam.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Petición del teléfono cuando hay un log-in de usuario
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Petición de teléfono para log-in de usuario y creación de usuario.")
public class PhoneRequest {


    @NotBlank(message = "El número no puede estar vacío")
    @Schema(description = "Número de teléfono del usuario", example = "+51940792465", required = true)
    private String number;

    @NotBlank(message = "El código de ciudad no puede estar vacío")
    @Schema(description = "Código de ciudad del usuario", example = "1", required = true)
    private Integer citycode;

    @NotBlank(message = "El código de país no puede estar vacío")
    @Schema(description = "Código del país del usuario", example = "12", required = true)
    private Integer contrycode;

}
