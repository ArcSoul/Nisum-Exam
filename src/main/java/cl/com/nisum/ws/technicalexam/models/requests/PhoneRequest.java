package cl.com.nisum.ws.technicalexam.models.requests;

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
public class PhoneRequest {


    @NotBlank(message = "El número no puede estar vacío")
    private String number;

    @NotBlank(message = "El código de ciudad no puede estar vacío")
    private Integer citycode;

    @NotBlank(message = "El código de país no puede estar vacío")
    private Integer contrycode;

}
