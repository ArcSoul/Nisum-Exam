package cl.com.nisum.ws.technicalexam.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Petición cuando hay un log-in de usuario
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Formato de petición de log-in de usuario para creación usuario")
public class LoginRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del usuario", example = "Anthony Martin Rosas Quispe", required = true)
    private String name;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9-]{2,}$", message = "El correo no tiene el formato correcto")
    @NotBlank(message = "El correo no puede estar vacío")
    @Schema(description = "Correo del usuario", example = "test@test.com", required = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min= 4, max= 20, message = "La contraseña debe tener entre 4 a 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2,})", message = "La contraseña no tiene el formato correcto")
    @Schema(description = "Contraseña del usuario", example = "latestAA153_", required = true)
    private String password;

    @NotEmpty(message = "La lista de teléfonos no puede estar vacía")
    @Schema(description = "Lista de los teléfonos asociados a el usuario")
    private List<PhoneRequest> phones;

}
