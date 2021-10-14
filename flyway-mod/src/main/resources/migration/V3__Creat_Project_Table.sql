DROP TABLE IF EXISTS project;
CREATE TABLE project
(
    id              BIGSERIAL PRIMARY KEY,
    organisation_id BIGINT REFERENCES organisation,
    description     TEXT NOT NULL,
    created_at      TIMESTAMP DEFAULT now(),
    updated_at      TIMESTAMP DEFAULT now()
);