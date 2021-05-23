package cl.com.nisum.ws.technicalexam.controllers;

import cl.com.nisum.ws.technicalexam.models.requests.LoginRequest;
import cl.com.nisum.ws.technicalexam.models.responses.LoginResponse;
import cl.com.nisum.ws.technicalexam.services.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<LoginResponse> logInUser(@RequestBody @Valid LoginRequest request) {
        return new ResponseEntity<>(logInService.logInUser(request), HttpStatus.CREATED);
    }


}
