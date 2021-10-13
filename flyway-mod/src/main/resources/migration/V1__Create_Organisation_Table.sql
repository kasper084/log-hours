DROP TABLE IF EXISTS organisation;
CREATE TABLE organisation
(
    id         BIGSERIAL PRIMARY KEY,
    name       TEXT NOT NULL,
    address    TEXT NOT NULL,
    info       TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

