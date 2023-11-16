CREATE TABLE patient (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(30) NOT NULL,
    phone_number    VARCHAR(12),
    email           VARCHAR(200),
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NOT NULL
);