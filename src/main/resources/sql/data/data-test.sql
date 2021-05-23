INSERT INTO NSM_USER(USR_ID, USR_NAME, USR_EMAIL, USR_PASSWORD, USR_STATUS, USR_LAST_LOGIN, USR_USER_CREATED,
                     USR_USER_UPDATED)
VALUES ('843fa052-82b3-4232-b32e-c60a1f2baab3', 'Anthony Rosas', 'test@test.cl',
        '$2y$12$aHQHe79Ky9ILrb1jpFX/BOWeEY35gwnjVEdeVCkiLqhoF.ltE9av.', 1, CURRENT_TIMESTAMP(), 'test@test.cl',
        'test@test.cl'),
       ('2a54a5ac-5321-4dc4-a1e7-25211b88777a', 'Martin Quispe', 'test1@test.cl',
        '$2y$12$yO7zEr9NumzLsq/YuquLeuT9e4M7LgXSzKmi28LKNRYTPxiDtxpuK', 1, CURRENT_TIMESTAMP(), 'test@test.cl',
        'test@test.cl');

INSERT INTO NSM_PHONE(POE_ID, POE_NUMBER, POE_CITY_CODE, POE_COUNTRY_CODE, POE_USR_ID, POE_USER_CREATED,
                      POE_USER_UPDATED)
VALUES ('81f26cae-ba39-405e-86ea-aadaf20385a0', '940792465', 1, 12, '843fa052-82b3-4232-b32e-c60a1f2baab3',
        'test@test.cl', 'test@test.cl'),
       ('36097f6c-dbb6-42c9-96c5-17fda6cf536a', '940792466', 1, 13, '843fa052-82b3-4232-b32e-c60a1f2baab3',
        'test@test.cl', 'test@test.cl'),
       ('a4388424-2747-4e16-b817-d0a8879783ee', '940792467', 1, 14, '2a54a5ac-5321-4dc4-a1e7-25211b88777a',
        'test@test.cl', 'test@test.cl');

INSERT INTO NSM_ROLE(ROL_ID, ROL_NAME, ROL_USER_CREATED, ROL_USER_UPDATED)
VALUES ('f1233e6b-8fb7-4b43-99cb-22e6c2458980', 'ROLE_USER', 'test@test.cl', 'test@test.cl'),
       ('3eeba11e-e3cc-483d-b5f9-00de5d5b4d99', 'ROLE_ADMIN', 'test@test.cl', 'test@test.cl');

INSERT INTO NSM_USER_IN_ROLE(UIR_USR_ID, UIR_ROL_ID)
VALUES ('843fa052-82b3-4232-b32e-c60a1f2baab3', 'f1233e6b-8fb7-4b43-99cb-22e6c2458980'),
       ('2a54a5ac-5321-4dc4-a1e7-25211b88777a', '3eeba11e-e3cc-483d-b5f9-00de5d5b4d99');

