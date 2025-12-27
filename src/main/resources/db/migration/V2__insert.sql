INSERT INTO permissions (name) VALUES
                                  ('ROLE_ADMIN'),
                                  ('ROLE_DOCTOR'),
                                  ('ROLE_USER'),
                                  ('ROLE_PATIENT');


INSERT INTO users (email, password, first_name, last_name) VALUES
                                                               ('doctor1@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'doctor1', 'doctor1'),
                                                               ('doctor2@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'doctor2', 'doctor2'),
                                                               ('patient1@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'doctor3', 'doctor3'),
                                                               ('patient2@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'doctor4', 'doctor4');


INSERT INTO doctors (id, specialization, years_of_experience) VALUES
                                                                 (1, 'Cardiologist', 10),
                                                                 (2, 'Therapist', 5);

INSERT INTO patients (id, medical_card_number) VALUES
                                                  (3, '1111'),
                                                  (4, '2222');

INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permissions p
WHERE u.email IN ('doctor1@mail.com', 'doctor2@mail.com')
  AND p.name = 'ROLE_DOCTOR';

INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permissions p
WHERE u.email IN ('patient1@mail.com', 'patient2@mail.com')
  AND p.name = 'ROLE_PATIENT';

INSERT INTO doctor_patients (doctor_id, patient_id) VALUES
                                                        (1, 3),
                                                        (1, 4),
                                                        (2, 3);

INSERT INTO appointments (doctor_id, patient_id, appointment_time, status, reason) VALUES
                                                                                      (1, 3, '2025-01-10 10:00:00', 'CREATED', 'Consultation'),
                                                                                      (1, 4, '2025-01-11 12:30:00', 'CONFIRMED', 'Zabolel'),
                                                                                      (2, 3, '2025-01-12 15:00:00', 'CANCELED', 'Patient unavailable');
