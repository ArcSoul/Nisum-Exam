package cl.com.nisum.ws.technicalexam.security;

import cl.com.nisum.ws.technicalexam.models.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Detalles del usuario personalizado
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CustomUserDetails implements UserDetails {

    private String email;
    private String name;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * @param user Entidad del Usuario a personalizar
     * @return Retorna un usuario en formato personalizado
     */
    public static CustomUserDetails build(UserEntity user) {

        /* Obteniendo los roles en formato de Spring Security */
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                authorities
        );
    }


    /**
     * @return Retorna una lista de roles en formato de spring security
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @return Retorna el password encriptado
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @return Retorna el usuario, en este caso el correo
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * @return Retorna si la cuenta no expira
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return Retorna si la cuenta no esta bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return Retorna si las credenciales no expiran
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return Retorna si el usuario esta habilitado
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
