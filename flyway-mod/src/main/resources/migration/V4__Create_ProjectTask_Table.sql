DROP TABLE IF EXISTS project_tasks;
CREATE TABLE project_tasks
(
    id          BIGSERIAL PRIMARY KEY,
    project_id  BIGINT REFERENCES project,
    description TEXT NOT NULL,
    employee_id BIGINT REFERENCES employee,
    hours       DOUBLE PRECISION,
    created_at  TIMESTAMP DEFAULT now(),
    updated_at  TIMESTAMP DEFAULT now()
);

