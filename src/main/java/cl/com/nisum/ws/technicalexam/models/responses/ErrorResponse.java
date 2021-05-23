package cl.com.nisum.ws.technicalexam.models.responses;

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
public class ErrorResponse {

    private String mensaje;
}
