DROP TABLE IF EXISTS organisations;
CREATE TABLE organisations
(
    id         BIGSERIAL PRIMARY KEY,
    name       TEXT                    NOT NULL,
    address    TEXT                    NOT NULL,
    info       TEXT                    NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL
);

