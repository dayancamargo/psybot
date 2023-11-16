CREATE TABLE evolution (
    id              SERIAL PRIMARY KEY,
    id_patient      INTEGER NOT NULL,
    id_psychiatrist INTEGER NOT NULL,
    description     VARCHAR(2000) NOT NULL,
    type            VARCHAR(100) NOT NULL,
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NOT NULL,
    CONSTRAINT evolution_patient_fk FOREIGN KEY (id_patient) REFERENCES patient (id),
    CONSTRAINT evolution_psychiatrist_fk FOREIGN KEY (id_psychiatrist) REFERENCES psychiatrist (id)
);
