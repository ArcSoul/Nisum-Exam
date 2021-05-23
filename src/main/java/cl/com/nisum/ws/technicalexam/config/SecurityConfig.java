package cl.com.nisum.ws.technicalexam.config;

import cl.com.nisum.ws.technicalexam.security.JWTAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Clase con el fin de activar la configuración de seguridad en nuestro aplicativo
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @return El gestor de autenticación para el ingreso del usuario
     * @throws Exception En caso de error si no se encuentra el gestor de autenticación
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @return Retorna el codificador para Bcrypt
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param http Permite configurar la seguridad de las peticiones HTTP
     * @throws Exception Ocurre cuando existe alguna excepción dentro del ambiente de seguridad
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* Desactivamos el filtro CSRF y activamos la configuración básica de CORS  */
        http.cors().and().csrf().disable();

        /* Modificamos la sesión a una gestión STATELESS, para desactivar el uso de COOKIES */
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        /* Configurando una excepción en caso ocurra algún error no identificado de las peticiones */
        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    "No esta autorizado para acceder a este recurso"
                            );
                        }
                )
                .and();

        /* Controlando los endpoints */
        http.authorizeRequests()
                /* Endpoints públicos, en este caso se ha abierto algunos con el fin de probar las funcionalidades */
                .antMatchers("/", "/v1/user/login").permitAll() // Login
                .antMatchers("/h2-console/**").permitAll() // consola de H2
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Documentación de API con Swagger
                .antMatchers("/actuator/**").permitAll() // Métricas del aplicativo Rest
                /* Endpoints privados */
                .anyRequest().authenticated()
                .and();

        /* Desactivamos la personalización de XFrameOptionsHeaderWriter */
        http.headers().frameOptions().disable()
                .and();

        /* Añadiendo filtros de json web token */
        http.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
