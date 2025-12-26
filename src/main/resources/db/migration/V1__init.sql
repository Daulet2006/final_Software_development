CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

CREATE TABLE permissions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_permissions (
    user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,

    CONSTRAINT pk_user_permissions PRIMARY KEY (user_id, permission_id),

    CONSTRAINT fk_up_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_up_permission
        FOREIGN KEY (permission_id)
            REFERENCES permissions(id)
            ON DELETE CASCADE
);

CREATE TABLE doctors (
    id BIGINT PRIMARY KEY,
    specialization VARCHAR(255),
    years_of_experience INT,

    CONSTRAINT fk_doctor_user
        FOREIGN KEY (id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE patients (
    id BIGINT PRIMARY KEY,
    medical_card_number VARCHAR(255),

    CONSTRAINT fk_patient_user
        FOREIGN KEY (id)
            REFERENCES users(id)
            ON DELETE CASCADE
);

CREATE TABLE doctor_patients (
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,

    CONSTRAINT pk_doctor_patients PRIMARY KEY (doctor_id, patient_id),

    CONSTRAINT fk_dp_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctors(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_dp_patient
        FOREIGN KEY (patient_id)
            REFERENCES patients(id)
            ON DELETE CASCADE
);

CREATE TABLE appointments (
    id BIGSERIAL PRIMARY KEY,

    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,

    appointment_time TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    reason TEXT,

    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctors(id)
            ON DELETE CASCADE,

    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id)
            REFERENCES patients(id)
            ON DELETE CASCADE
);
