package cl.com.nisum.ws.technicalexam.services;

import cl.com.nisum.ws.technicalexam.constants.RoleEnum;
import cl.com.nisum.ws.technicalexam.constants.StatusEnum;
import cl.com.nisum.ws.technicalexam.models.entities.PhoneEntity;
import cl.com.nisum.ws.technicalexam.models.entities.RoleEntity;
import cl.com.nisum.ws.technicalexam.models.entities.UserEntity;
import cl.com.nisum.ws.technicalexam.models.requests.LoginRequest;
import cl.com.nisum.ws.technicalexam.models.requests.PhoneRequest;
import cl.com.nisum.ws.technicalexam.models.responses.LoginResponse;
import cl.com.nisum.ws.technicalexam.repositories.RoleRepository;
import cl.com.nisum.ws.technicalexam.repositories.UserRepository;
import cl.com.nisum.ws.technicalexam.utils.JWTUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Capa de lógica de negocio para el log-in
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Service
@Log4j2
public class LogInService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @param userRepository Bean de la capa de acceso a datos de la entidad usuarios
     * @param roleRepository Bean de la capa de acceso a datos de la entidad rol
     * @param authenticationManager Bean para el gestor de la autenticación
     * @param passwordEncoder Bean para la encriptación de contraseñas con Bcrypt
     * @param jwtUtils Bean de utilidades de Json Web Tokens
     */
    @Autowired
    public LogInService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JWTUtils jwtUtils
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }


    /**
     * @param request Petición del log-in
     * @return Retorna una respuesta del log-in
     */
    @Transactional
    public LoginResponse logInUser(LoginRequest request) {
        String userIDGenerated;
        String tokenGenerated;
        String userStateForDefault;
        RoleEntity userRoleFound;
        UserEntity userToInsert;
        UserEntity userRegistered;
        List<PhoneEntity> phonesToInsert;
        Set<RoleEntity> rolesToInsert;
        Authentication authentication;
        UsernamePasswordAuthenticationToken userProtected;

        log.info("Verificando que el correo exista");
        /* Buscando el usuario por correo */
        Optional<UserEntity> result = userRepository.findByEmail(request.getEmail());

        /* Si existe el usuario entonces el correo ya esta registrado */
        if (result.isPresent()) {
            log.warn("El correo [{}] ya esta registrado en la base de datos", request.getEmail());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo ya registrado");
        } else { /* Si no se procede a crear el usuario */
            log.info("Generando el Id del usuario");
            /* Generamos el uuid del usuario */
            userIDGenerated = UUID.randomUUID().toString();

            /* Inicializando las listas */
            phonesToInsert = new ArrayList<>();
            rolesToInsert = new HashSet<>();

            log.info("Buscando el rol del usuario por defecto");
            /* Buscamos el rol del usuario por defecto, si no se encuentra salta una excepción de error del lado del servidor */
            userRoleFound = roleRepository.findByName(RoleEnum.ROLE_USER.toString())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Rol no encontrado"));

            /* Añadimos el rol a la lista de roles del usuario */
            rolesToInsert.add(userRoleFound);

            /* Preparamos el usuario para insertarse con el patron de diseño Builder */
            userToInsert = UserEntity.builder()
                    .id(userIDGenerated)
                    .email(request.getEmail())
                    .name(request.getName())
                    .status(StatusEnum.STATUS_ACTIVE.getCode())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userCreated(request.getEmail())
                    .userUpdated(request.getEmail())
                    .phones(phonesToInsert)
                    .roles(rolesToInsert)
                    .build();

            /* Iteramos todos los teléfonos del usuario */
            for (PhoneRequest phone : request.getPhones()) {
                /* Añadimos cada teléfono del usuario con ayuda del patrón de diseño Builder */
                phonesToInsert.add(
                        PhoneEntity.builder()
                                .id(UUID.randomUUID().toString())
                                .number(phone.getNumber())
                                .cityCode(phone.getCitycode())
                                .countryCode(phone.getContrycode())
                                .user(userToInsert)
                                .userCreated(request.getEmail())
                                .userUpdated(request.getEmail())
                                .build()
                );
            }

            log.info("Guardando el usuario con correo [{}]", request.getEmail());
            /* Guardamos y ejecutamos todas las peticiones en proceso */
            userRegistered = userRepository.saveAndFlush(userToInsert);
            /* Refrescando la entidad para obtener los valores por defecto de las fechas de creación y modificación */
            entityManager.refresh(userRegistered);

            /* Pasamos al formato de spring security para obtener un usuario protegido */
            userProtected = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            /* Y autenticamos con el gestor de autenticación */
            authentication = authenticationManager.authenticate(userProtected);

            /* Establecemos la autenticación en el contexto de Spring */
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Generando el Json Web Token al usuario con correo [{}]", request.getEmail());
            /* Generamos el Json Web Token */
            tokenGenerated = jwtUtils.getToken(authentication);

            /* Obtenemos el estado del usuario */
            userStateForDefault = StatusEnum.STATUS_ACTIVE.getCode() == userRegistered.getStatus() ? StatusEnum.STATUS_ACTIVE.getName() : StatusEnum.STATUS_INACTIVE.getName();

            log.info("Proceso de creación de usuario con correo [{}] completada", request.getEmail());
            /* Con ayuda del patron de diseño Builder obtenemos el log-in response */
            return LoginResponse.builder()
                    .id(userIDGenerated)
                    .created(userRegistered.getDateCreated())
                    .modified(userRegistered.getDateUpdated())
                    .last_login(userRegistered.getLastLogin())
                    .token(tokenGenerated)
                    .isactive(userStateForDefault)
                    .build();
        }
    }
}
