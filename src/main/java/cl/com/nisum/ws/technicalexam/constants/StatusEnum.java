package cl.com.nisum.ws.technicalexam.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Constantes para los estados de los usuarios
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    STATUS_ACTIVE(1, "Activo"),
    STATUS_INACTIVE(0, "Inactivo")
    ;
    private final int code;
    private final String name;
}
