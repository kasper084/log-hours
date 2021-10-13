DROP TABLE IF EXISTS project_task;
CREATE TABLE project_task
(
    id          BIGSERIAL PRIMARY KEY,
    project_id  BIGINT REFERENCES project (id),
    description TEXT NOT NULL,
    employee_id BIGINT REFERENCES employee (id),
    hours       DOUBLE PRECISION,
    created_at  TIMESTAMP DEFAULT now(),
    updated_at  TIMESTAMP DEFAULT now()
);

