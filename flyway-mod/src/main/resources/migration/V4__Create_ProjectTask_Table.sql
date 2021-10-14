DROP TABLE IF EXISTS project_tasks;
CREATE TABLE project_tasks
(
    id          BIGSERIAL PRIMARY KEY,
    project_id  BIGINT REFERENCES projects,
    description TEXT NOT NULL,
    employee_id BIGINT REFERENCES employees,
    hours       DOUBLE PRECISION,
    created_at  TIMESTAMP DEFAULT now(),
    updated_at  TIMESTAMP DEFAULT now()
);

