package cl.com.nisum.ws.technicalexam.repositories;

import cl.com.nisum.ws.technicalexam.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

/**
 * Capa de acceso a datos de la entidad Usuario
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
}
