DROP TABLE IF EXISTS projects;
CREATE TABLE projects
(
    id              BIGSERIAL PRIMARY KEY,
    organisation_id BIGINT REFERENCES organisations,
    description     TEXT NOT NULL,
    created_at      TIMESTAMP DEFAULT now() NOT NULL,
    updated_at      TIMESTAMP DEFAULT now() NOT NULL
);