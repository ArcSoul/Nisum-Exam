package cl.com.nisum.ws.technicalexam.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

/**
 * Plantilla de Respuesta cuando un usuario ha ingresado.
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
@Schema(description = "Respuesta de la creación y log-in del usuario")
public class LoginResponse {

    @Schema(description = "ID del usuario en formato UUID")
    private String id;

    @Schema(description = "Fecha de creación del usuario")
    private Date created;

    @Schema(description = "Fecha de modificación del usuario")
    private Date modified;

    @Schema(description = "Fecha de último log-in del usuario")
    private Date last_login;

    @Schema(description = "Token del usuario en formato Json Web Token")
    private String token;

    @Schema(description = "Si esta activado el usuario")
    private String isactive;

}
