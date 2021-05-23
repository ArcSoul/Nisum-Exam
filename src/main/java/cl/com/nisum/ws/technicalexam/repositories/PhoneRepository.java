package cl.com.nisum.ws.technicalexam.repositories;

import cl.com.nisum.ws.technicalexam.models.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Capa de acceso a datos para la entidad Teléfono
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {
}
