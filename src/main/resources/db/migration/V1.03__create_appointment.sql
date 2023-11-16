CREATE TABLE appointment (
    id              SERIAL PRIMARY KEY,
    id_patient      INTEGER NOT NULL,
    id_psychiatrist INTEGER NOT NULL,
    absence         INTEGER NOT NULL default 0,
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NOT NULL,
    CONSTRAINT appointment_patient_fk FOREIGN KEY (id_patient) REFERENCES patient (id),
    CONSTRAINT appointment_psychiatrist_fk FOREIGN KEY (id_psychiatrist) REFERENCES psychiatrist (id)
);
