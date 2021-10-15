DROP TABLE IF EXISTS employees;
CREATE TABLE employees
(
    id              BIGSERIAL PRIMARY KEY,
    name            TEXT                    NOT NULL,
    organisation_id BIGINT REFERENCES organisations,
    specialisation  TEXT                    NOT NULL,
    hour_cost       DOUBLE PRECISION        NOT NULL,
    created_at      TIMESTAMP DEFAULT now() NOT NULL,
    updated_at      TIMESTAMP DEFAULT now() NOT NULL
);

