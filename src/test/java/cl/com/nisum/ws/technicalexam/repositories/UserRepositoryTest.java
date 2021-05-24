package cl.com.nisum.ws.technicalexam.repositories;

import cl.com.nisum.ws.technicalexam.data.DataTest;
import cl.com.nisum.ws.technicalexam.models.entities.UserEntity;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas a la capa de acceso a datos, {@link UserRepository}
 *
 * @author Anthony Martin Rosas Quispe
 * @version 1.0
 */
@DataJpaTest
@Disabled
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    /**
     * Pruebas de búsqueda por correo del usuario cuando es exitoso
     */
    @Test
    void findByEmail() {
        Optional<UserEntity> userFound = userRepository.findByEmail("test@test.cl");

        assertTrue(userFound.isPresent());
        assertEquals("test@test.cl", userFound.get().getEmail());
    }

    /**
     * Pruebas de búsqueda por correo del usuario, pero cuando no lo encuentra
     */
    @Test
    void findByEmailThrowException() {
        Optional<UserEntity> userFound = userRepository.findByEmail("test@test.pe");

        assertThrows(NoSuchElementException.class, userFound::get);
        assertFalse(userFound.isPresent());
    }

    /**
     * Pruebas cuando guarda un usuario en la base de datos
     */
    @Test
    void save() {
        Optional<UserEntity> userFound;

        userRepository.save(DataTest.USER_TEST_1);
        userFound = userRepository.findByEmail("test@test.com");

        assertTrue(userFound.isPresent());
        assertEquals(DataTest.USER_TEST_1.getEmail(), userFound.get().getEmail());
        assertEquals(DataTest.USER_TEST_1.getName(), userFound.get().getName());
        assertEquals(DataTest.USER_TEST_1.getId(), userFound.get().getId());
    }


}
