package cl.com.nisum.ws.technicalexam.repositories;

import cl.com.nisum.ws.technicalexam.models.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Capa de acceso a datos de la entidad Rol
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    public Optional<RoleEntity> findByName(String name);
}
