package cl.com.nisum.ws.technicalexam.utils;

import cl.com.nisum.ws.technicalexam.security.CustomUserDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Bean con utilidades del Json Web Token
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Component
@Log4j2
public class JWTUtils {

    @Value(value = "${jwt.expiration.time}")
    public long EXPIRATION_TIME;

    @Value(value = "${jwt.secret.key}")
    public String TOKEN_SECRET;

    @Value(value = "${security.token.bearer.prefix}")
    public String TOKEN_BEARER_PREFIX;

    @Value(value = "${security.header.authorization.key}")
    public String HEADER_AUTHORIZATION_KEY;

    /**
     * @param authentication Establecemos la autenticación para obtener los detalles del usuario
     * @return Se retorna el Json Web Token con formato Bearer firmado con el token secreto
     */
    public String getToken(Authentication authentication) {
        /* Se obtiene los detalles de usuario personalizado */
        CustomUserDetails userAuthenticated = (CustomUserDetails) authentication.getPrincipal();

        /* Se genera el Json Web Token con el tiempo de expiración y se firma con la llave secreta */
        String token = JWT.create()
                .withSubject(userAuthenticated.getEmail()) // Añadiendo el asunto con el email del usuario
                .withIssuedAt(new Date()) // Añadiendo fecha de creación del token
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Añadiendo la expiración del token
                .sign(Algorithm.HMAC512(TOKEN_SECRET)); // Firmando con el algoritmo HMAC512 y el token secreto

        return TOKEN_BEARER_PREFIX + " " + token;
    }

    /**
     * @param token Token a para decodificar el correo
     * @return Retorna el correo del usuario o el asunto del Json Web Token
     */
    public String getEmailFromToken(String token) {
        return JWT.decode(token).getSubject();
    }

    /**
     * @param token El Json Web Token a validar
     * @return Retorna si el token es válido o no
     */
    public boolean validateToken(String token) {
        Algorithm algorithm;
        JWTVerifier verifier;

        /* Constante para saber que significa el TRUE */
        boolean TokenIsValid = true;

        try {
            algorithm = Algorithm.HMAC512(TOKEN_SECRET); /* Se verifica el algoritmo con el Token */
            verifier = JWT.require(algorithm).build(); /* Se inserta el algoritmo y se crea el verificador */
            verifier.verify(token); /* Se verifica el token si es incorrecto dara excepción */

            return TokenIsValid;
        } catch (JWTVerificationException e) {
            log.error("El token ya no es valido o ha ocurrido un error a la hora de verificar el token");
            e.printStackTrace();
        }

        return !TokenIsValid;
    }
}
