package cl.com.nisum.ws.technicalexam.repositories;

import cl.com.nisum.ws.technicalexam.models.entities.RoleEntity;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas a la capa de acceso a datos, {@link RoleRepository}
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@DataJpaTest
@Disabled
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    /**
     * Pruebas de búsqueda por nombre del rol cuando es exitosa
     */
    @Test
    void findByName() {
        Optional<RoleEntity> roleFound = roleRepository.findByName("ROLE_USER");

        assertTrue(roleFound.isPresent());
        assertEquals("ROLE_USER", roleFound.get().getName());
    }

    /**
     * Prueba por búsqueda por nombre del rol, pero cuando no lo encuentra
     */
    @Test
    void findByNameThrowException() {
        Optional<RoleEntity> roleFound = roleRepository.findByName("ROLE_MODERATOR");

        assertThrows(NoSuchElementException.class, roleFound::get);
        assertFalse(roleFound.isPresent());
    }


}
