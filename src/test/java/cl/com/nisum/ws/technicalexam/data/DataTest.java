package cl.com.nisum.ws.technicalexam.data;

import cl.com.nisum.ws.technicalexam.models.entities.PhoneEntity;
import cl.com.nisum.ws.technicalexam.models.entities.RoleEntity;
import cl.com.nisum.ws.technicalexam.models.entities.UserEntity;
import cl.com.nisum.ws.technicalexam.models.requests.LoginRequest;
import cl.com.nisum.ws.technicalexam.models.requests.PhoneRequest;
import cl.com.nisum.ws.technicalexam.models.responses.LoginResponse;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class DataTest {

    public static final Date NOW = new Date();

    public static final RoleEntity ROLE_MODERATOR = RoleEntity.builder()
            .id("091b7543-4f3f-4998-911c-38dd157e0688")
            .name("ROLE_MODERATOR")
            .dateCreated(NOW)
            .dateUpdated(NOW)
            .userCreated("test@test.com")
            .userUpdated("test@test.com")
            .build();

    public static final PhoneEntity PHONE_TEST_1 = PhoneEntity.builder()
            .id("342f57bd-78cf-49f5-a272-8ac181d4ba2d")
            .cityCode(1)
            .countryCode(12)
            .number("+5178246525")
            .user(DataTest.USER_TEST_1)
            .dateCreated(NOW)
            .dateUpdated(NOW)
            .userCreated("test@test.com")
            .userUpdated("test@test.com")
            .build();

    public static final PhoneEntity PHONE_TEST_2 = PhoneEntity.builder()
            .id("c465cfb9-d684-4727-897f-5b556de77775")
            .cityCode(3)
            .countryCode(29)
            .number("+5116354165")
            .user(DataTest.USER_TEST_2)
            .dateCreated(NOW)
            .dateUpdated(NOW)
            .userCreated("test1@test.com")
            .userUpdated("test1@test.com")
            .build();

    public static final PhoneEntity PHONE_TEST_3 = PhoneEntity.builder()
            .id("203dace0-f7a0-40c1-a31f-84f50a57cc87")
            .cityCode(9)
            .countryCode(3)
            .number("+51154135185")
            .user(DataTest.USER_TEST_3)
            .dateCreated(NOW)
            .dateUpdated(NOW)
            .userCreated("test2@test.com")
            .userUpdated("test2@test.com")
            .build();

    public static final UserEntity USER_TEST_1 = UserEntity.builder()
            .id("e563b5f8-e777-41c5-a1e0-fc0619b2317b")
            .email("test@test.com")
            .name("Anthony Rosas")
            .password("$2y$12$Ox/zaMcRN6AItuJpfUw8ze/RdLL2EFhUes/wvAc.TvvrdWMLZ14au")
            .status(1)
            .lastLogin(NOW)
            .dateCreated(NOW)
            .dateUpdated(NOW)
            .userCreated("test@test.com")
            .userUpdated("test@test.com")
            .phones(Collections.singletonList(PHONE_TEST_1))
            .build();

    public static final UserEntity USER_TEST_2 = UserEntity.builder()
            .id("9c43718f-b26f-41bd-9364-57709a888938")
            .email("test1@test.com")
            .name("Martin Rosas")
            .password("$2y$12$twBKLmvcFd76h5hra5DWQexcbNPFvrh/F6dqBk/0aucdcvzOdnrQ.")
            .status(1)
            .lastLogin(NOW)
            .dateCreated(NOW)
            .dateUpdated(NOW)
            .userCreated("test1@test.com")
            .userUpdated("test1@test.com")
            .phones(Collections.singletonList(PHONE_TEST_2))
            .build();

    public static final UserEntity USER_TEST_3 = UserEntity.builder()
            .id("0cda11bb-026e-49c9-befe-6924c0b6b773")
            .email("test2@test.com")
            .name("Rosas Quispe")
            .password("$2y$12$2UL0Z3P/ZD0twfXK2O9OGeip2.WSqsA2Xy49Kpjapquc3p4C5imGO")
            .status(1)
            .lastLogin(NOW)
            .dateCreated(NOW)
            .dateUpdated(NOW)
            .userCreated("test2@test.com")
            .userUpdated("test2@test.com")
            .phones(Collections.singletonList(PHONE_TEST_3))
            .build();

    public static final PhoneRequest PHONE_REQUEST_1 = new PhoneRequest("+5178246525", 1, 12);
    public static final PhoneRequest PHONE_REQUEST_2 = new PhoneRequest("+5116354165", 3, 29);
    public static final PhoneRequest PHONE_REQUEST_3 = new PhoneRequest("+51154135185", 9, 3);

    public static final LoginRequest LOGIN_REQUEST_1 = new LoginRequest("Anthony Rosas", "test@test.com", "testAA123_", Collections.singletonList(PHONE_REQUEST_1));
    public static final LoginRequest LOGIN_REQUEST_2 = new LoginRequest("Martin Rosas", "test1@test.com", "test", Collections.singletonList(PHONE_REQUEST_2));
    public static final LoginRequest LOGIN_REQUEST_3 = new LoginRequest("Rosas Quispe", "test@test.cl", "testAA123_", Collections.singletonList(PHONE_REQUEST_3));

    public static final LoginResponse LOGIN_RESPONSE_1 = LoginResponse.builder()
            .id("e563b5f8-e777-41c5-a1e0-fc0619b2317b")
            .isactive("Activo")
            .last_login(NOW)
            .created(NOW)
            .modified(NOW)
            .token("TOKEN")
            .build();

    public static final LoginResponse LOGIN_RESPONSE_2 = LoginResponse.builder()
            .id("9c43718f-b26f-41bd-9364-57709a888938")
            .isactive("Activo")
            .last_login(NOW)
            .created(NOW)
            .modified(NOW)
            .token("TOKEN")
            .build();

    public static final LoginResponse LOGIN_RESPONSE_3 = LoginResponse.builder()
            .id("0cda11bb-026e-49c9-befe-6924c0b6b773")
            .isactive("Activo")
            .last_login(NOW)
            .created(NOW)
            .modified(NOW)
            .token("TOKEN")
            .build();
}
