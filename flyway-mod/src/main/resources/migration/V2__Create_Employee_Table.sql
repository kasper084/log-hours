DROP TABLE IF EXISTS employees;
CREATE TABLE employees
(
    id              BIGSERIAL PRIMARY KEY,
    name            TEXT NOT NULL,
    organisation_id BIGINT REFERENCES organisations,
    specialisation  TEXT NOT NULL,
    hour_cost       DOUBLE PRECISION,
    created_at      TIMESTAMP DEFAULT now(),
    updated_at      TIMESTAMP DEFAULT now()
);

