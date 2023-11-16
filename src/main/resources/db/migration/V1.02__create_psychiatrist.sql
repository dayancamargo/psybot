CREATE TABLE psychiatrist (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(30) NOT NULL,
    phone_number    VARCHAR(12),
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NOT NULL
);
