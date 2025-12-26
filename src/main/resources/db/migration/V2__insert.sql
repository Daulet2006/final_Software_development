INSERT INTO permission (name) VALUES
                                  ('ROLE_ADMIN'),
                                  ('ROLE_DOCTOR'),
                                  ('ROLE_PATIENT');

INSERT INTO users (email, password, first_name, last_name) VALUES
                                                               ('doctor1@mail.com', '$2a$10$doctorhash', 'John', 'Smith'),
                                                               ('doctor2@mail.com', '$2a$10$doctorhash', 'Emily', 'Brown'),
                                                               ('patient1@mail.com', '$2a$10$patienthash', 'Alice', 'Johnson'),
                                                               ('patient2@mail.com', '$2a$10$patienthash', 'Bob', 'Williams');


INSERT INTO doctor (id, specialization, years_of_experience) VALUES
                                                                 (1, 'Cardiologist', 10),
                                                                 (2, 'Therapist', 5);

INSERT INTO patient (id, medical_card_number) VALUES
                                                  (3, 'MC-1001'),
                                                  (4, 'MC-1002');

INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permission p
WHERE u.email IN ('doctor1@mail.com', 'doctor2@mail.com')
  AND p.name = 'ROLE_DOCTOR';

INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permission p
WHERE u.email IN ('patient1@mail.com', 'patient2@mail.com')
  AND p.name = 'ROLE_PATIENT';

INSERT INTO doctor_patients (doctor_id, patient_id) VALUES
                                                        (1, 3),
                                                        (1, 4),
                                                        (2, 3);

INSERT INTO appointment (doctor_id, patient_id, appointment_time, status, reason) VALUES
                                                                                      (1, 3, '2025-01-10 10:00:00', 'CREATED', 'Initial consultation'),
                                                                                      (1, 4, '2025-01-11 12:30:00', 'CONFIRMED', 'Blood pressure issues'),
                                                                                      (2, 3, '2025-01-12 15:00:00', 'CANCELED', 'Patient unavailable');
