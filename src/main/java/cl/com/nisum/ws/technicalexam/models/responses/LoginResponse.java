package cl.com.nisum.ws.technicalexam.models.responses;

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
public class LoginResponse {

    private String id;
    private Date created;
    private Date modified;
    private Date last_login;
    private String token;
    private String isactive;

}
