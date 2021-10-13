DROP TABLE IF EXISTS employee;
CREATE TABLE employee
(
    id                BIGSERIAL PRIMARY KEY,
    name              TEXT NOT NULL,
    organisation_name TEXT REFERENCES organisation (name),
    specialisation    TEXT NOT NULL,
    hour_cost         DOUBLE PRECISION,
    created_at        TIMESTAMP DEFAULT now(),
    updated_at        TIMESTAMP DEFAULT now()
);

