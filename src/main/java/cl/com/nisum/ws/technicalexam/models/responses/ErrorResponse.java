package cl.com.nisum.ws.technicalexam.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Plantilla de mensaje de errores para peticiones REST
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta por defecto de los mensajes de error")
public class ErrorResponse {

    @Schema(description = "Descripción del mensaje de error", example = "El correo ya esta registrado")
    private String mensaje;
}
