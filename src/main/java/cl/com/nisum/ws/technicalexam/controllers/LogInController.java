package cl.com.nisum.ws.technicalexam.controllers;

import cl.com.nisum.ws.technicalexam.models.entities.UserEntity;
import cl.com.nisum.ws.technicalexam.models.requests.LoginRequest;
import cl.com.nisum.ws.technicalexam.models.responses.ErrorResponse;
import cl.com.nisum.ws.technicalexam.models.responses.LoginResponse;
import cl.com.nisum.ws.technicalexam.services.LogInService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controlador Rest para el api de usuarios
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@RestController
@RequestMapping("/v1/user")
@Tag(name = "User", description = "Servicios relacionados a usuario")
public class LogInController {

    private final LogInService logInService;

    /**
     * @param logInService Inyectamos el Bean {@link LogInService}
     */
    @Autowired
    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    /**
     * @param request Petición para el log-in del usuario
     * @return Retorna una respuesta con un estado HTTP
     */
    @PostMapping("/login")
    @Timed(value = "users.login.user", description = "Métricas de las peticiones al servicio /v1/user/login")
    @Operation(
            summary = "Servicio de Login de usuarios",
            description = "Permite crear un usuario si no existe el correo y poder hacer un log-in",
            tags = {"User"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cuando se creo el usuario correctamente y hizo log-in",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cuando ha ocurrido un error a nivel de validación de campos o otros tipos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Cuando ha ocurrido un error de que el correo ya esta registrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Cuando ha ocurrido errores internos en el servicio",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
    })
    public ResponseEntity<LoginResponse> logInUser(@RequestBody @Valid LoginRequest request) {
        return new ResponseEntity<>(logInService.logInUser(request), HttpStatus.CREATED);
    }



}
