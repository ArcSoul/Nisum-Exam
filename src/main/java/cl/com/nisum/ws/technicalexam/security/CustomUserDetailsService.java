package cl.com.nisum.ws.technicalexam.security;

import cl.com.nisum.ws.technicalexam.models.entities.UserEntity;
import cl.com.nisum.ws.technicalexam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Personalizando el servicio de detalles de usuario
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * @param userRepository Inyectando el bean {@link UserRepository}
     */
    @Autowired
    public CustomUserDetailsService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    /**
     * @param username Ingresando el usuario para búsqueda y aplicaciones de buenas practicas de Spring Security
     * @return Retornando los usuarios en la interface {@link UserDetails}
     * @throws UsernameNotFoundException En caso de que ocurra algún error de que no se encontró el usuario
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user;
        Optional<UserEntity> result = userRepository.findByEmail(username); // Buscando el usuario por correo

        /* Verificando si el usuario existe */
        if (result.isPresent()) {
            user = result.get(); // Obteniendo el usuario
            return CustomUserDetails.build(user);
        } else {
            throw new UsernameNotFoundException("Usuario con email " + username + " no encontrado");
        }
    }

}
