package cl.com.nisum.ws.technicalexam.controllers;

import cl.com.nisum.ws.technicalexam.services.LogInService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static cl.com.nisum.ws.technicalexam.data.DataTest.LOGIN_REQUEST_1;
import static cl.com.nisum.ws.technicalexam.data.DataTest.LOGIN_REQUEST_2;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pruebas a la capa de controlador del log-in de usuarios
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class LogInControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LogInService logInService;

    ObjectMapper objectMapper;

    /**
     * Pre inicializando el object mapper para la creación de texto json plano
     */
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Pruebas al endpoint /v1/user/login cuando es exitoso.
     * @throws Exception En caso falle la petición
     */
    @Test
    void logInUser() throws Exception {
        mvc.perform(post("/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LOGIN_REQUEST_1)))
                .andExpect(status().isCreated());
    }

    /**
     * Pruebas al endpoint /v1/user/login cuando es ocurre un error de validación
     * @throws Exception En caso falle la petición
     */
    @Test
    void logInUserValidationError() throws Exception {
        mvc.perform(post("/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LOGIN_REQUEST_2)))
                .andExpect(status().isBadRequest());
    }
}
