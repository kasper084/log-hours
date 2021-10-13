DROP TABLE IF EXISTS employee;
CREATE TABLE employee
(
    id                BIGSERIAL PRIMARY KEY,
    name              TEXT NOT NULL,
    last_name         TEXT,
    organisation_name TEXT,
    specialisation    TEXT NOT NULL,
    hour_cost         DOUBLE PRECISION,
    created_at        TIMESTAMP DEFAULT now(),
    updated_at        TIMESTAMP DEFAULT now()
);

