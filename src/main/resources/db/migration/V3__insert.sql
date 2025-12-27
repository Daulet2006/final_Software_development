INSERT INTO users (email, password, first_name, last_name) VALUES
                                                               ('admin@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'Admin', 'Root'),
                                                               ('user1@mail.com',  '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'User', 'One'),
                                                               ('user2@mail.com',  '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'User', 'Two');


INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permissions p
WHERE u.email = 'admin@mail.com'
  AND p.name IN ('ROLE_ADMIN','ROLE_USER');

INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permissions p
WHERE u.email IN ('user1@mail.com','user2@mail.com')
  AND p.name = 'ROLE_USER';


INSERT INTO users (email, password, first_name, last_name) VALUES
                                                               ('doctor3@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'Anna', 'Taylor'),
                                                               ('doctor4@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'David', 'Miller');

INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permissions p
WHERE u.email IN ('doctor3@mail.com','doctor4@mail.com')
  AND p.name = 'ROLE_DOCTOR';

INSERT INTO doctors (id, specialization, years_of_experience)
SELECT u.id, 'Neurologist', 8 FROM users u WHERE u.email='doctor3@mail.com'
UNION ALL
SELECT u.id, 'Pediatrician', 12 FROM users u WHERE u.email='doctor4@mail.com';


INSERT INTO users (email, password, first_name, last_name) VALUES
                                                               ('patient3@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'Kate', 'Moore'),
                                                               ('patient4@mail.com', '$2a$12$WuSgMbSvKlhPbU/Y886f5ufiMVqJgYQzcy3C8Hb2mJkBRPHPzjUgS', 'Tom', 'Harris');

INSERT INTO user_permissions (user_id, permission_id)
SELECT u.id, p.id
FROM users u, permissions p
WHERE u.email IN ('patient3@mail.com','patient4@mail.com')
  AND p.name = 'ROLE_PATIENT';

INSERT INTO patients (id, medical_card_number)
SELECT u.id, '3333' FROM users u WHERE u.email='patient3@mail.com'
UNION ALL
SELECT u.id, '4444' FROM users u WHERE u.email='patient4@mail.com';


INSERT INTO doctor_patients (doctor_id, patient_id)
SELECT d.id, p.id
FROM doctors d, patients p
WHERE d.specialization='Neurologist' AND p.medical_card_number IN ('3333','4444');

INSERT INTO doctor_patients (doctor_id, patient_id)
SELECT d.id, p.id
FROM doctors d, patients p
WHERE d.specialization='Pediatrician' AND p.medical_card_number IN ('2222','3333');


INSERT INTO appointments (doctor_id, patient_id, appointment_time, status, reason) VALUES
                                                                                       ((SELECT id FROM doctors WHERE specialization='Neurologist' LIMIT 1),
                                                                                        (SELECT id FROM patients WHERE medical_card_number='3333'),
                                                                                        '2025-01-20 10:00:00', 'CREATED', 'Headache'),

                                                                                       ((SELECT id FROM doctors WHERE specialization='Neurologist' LIMIT 1),
                                                                                        (SELECT id FROM patients WHERE medical_card_number='4444'),
                                                                                        '2025-01-21 11:30:00', 'CONFIRMED', 'Memory loss'),

                                                                                       ((SELECT id FROM doctors WHERE specialization='Pediatrician' LIMIT 1),
                                                                                        (SELECT id FROM patients WHERE medical_card_number='2222'),
                                                                                        '2025-01-22 09:00:00', 'COMPLETED', 'Child flu'),

                                                                                       ((SELECT id FROM doctors WHERE specialization='Pediatrician' LIMIT 1),
                                                                                        (SELECT id FROM patients WHERE medical_card_number='3333'),
                                                                                        '2025-01-23 14:00:00', 'CREATED', 'Vaccination');
