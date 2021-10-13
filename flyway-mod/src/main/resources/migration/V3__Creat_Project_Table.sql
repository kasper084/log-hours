DROP TABLE IF EXISTS project;
CREATE TABLE project
(
    id                BIGSERIAL PRIMARY KEY,
    organisation_name TEXT NOT NULL REFERENCES organisation (name),
    description       TEXT NOT NULL,
    created_at        TIMESTAMP DEFAULT now(),
    updated_at        TIMESTAMP DEFAULT now()
);