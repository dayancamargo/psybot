CREATE TABLE patient_illness (
    id              SERIAL PRIMARY KEY,
    id_cid          INTEGER NOT NULL,
    id_patient      INTEGER NOT NULL,
    description     VARCHAR(2000) NOT NULL,
    diagnosed_at    DATE NOT NULL,
    until_at        DATE,
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NOT NULL,
    CONSTRAINT patient_illness_patient_fk FOREIGN KEY (id_patient) REFERENCES patient(id),
    CONSTRAINT patient_illness_cid_fk FOREIGN KEY (id_cid) REFERENCES cid (id)
);