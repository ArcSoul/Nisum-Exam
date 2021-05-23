package cl.com.nisum.ws.technicalexam.exceptions;

import cl.com.nisum.ws.technicalexam.models.responses.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;

/**
 * Personalizando las peticiones comunes de los API REST
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@ControllerAdvice
public class CustomRestException extends ResponseEntityExceptionHandler {

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param headers Los header que componen la excepción
     * @param status  El estado del HTTP
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String message;
        ErrorResponse errorResponse;
        StringBuilder errors = new StringBuilder();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); // Buscando la lista de errores por validación

        /* Iterando los errores de validación  */
        for (FieldError fieldError : fieldErrors) {
            message = fieldError.getDefaultMessage(); // Obteniendo los mensaje por defectos de cada validación
            errors.append(message).append(". "); // Separando cada mensaje
        }

        /* Creando el retorno de la respuesta */
        errorResponse = new ErrorResponse(errors.toString());

        return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param headers Los header que componen la excepción
     * @param status  El estado del HTTP
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request
    ) {
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request
    ) {
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param headers Los header que componen la excepción
     * @param status  El estado del HTTP
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param headers Los header que componen la excepción
     * @param status  El estado del HTTP
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param headers Los header que componen la excepción
     * @param status  El estado del HTTP
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        /* Obteniendo el mensaje en concreto sin el estado HTTP */
        String message = Objects.requireNonNull(ex.getLocalizedMessage()).split("\"")[1];
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(message);

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), ex.getStatus());
    }

    /**
     * @param ex      Es la excepción llamada cuando ocurre un error
     * @param request La petición web
     * @return Retorna un ReponseEntity el cual es el retorno del API REST
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        /* Creando el retorno de la respuesta */
        ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
