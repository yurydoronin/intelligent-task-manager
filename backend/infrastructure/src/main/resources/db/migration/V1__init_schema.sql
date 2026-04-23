-- Таблица для Task
CREATE TYPE priority AS ENUM ('HIGH', 'MIDDLE', 'LOW');
CREATE TYPE status AS ENUM ('WAIT', 'WORK', 'DONE');

CREATE TABLE task
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(100)             NOT NULL,
    description  VARCHAR(255),
    priority     priority                 NOT NULL,
    status       status                   NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    deadline     TIMESTAMP WITH TIME ZONE,
    completed_at TIMESTAMP WITH TIME ZONE
);

-- базовые
CREATE INDEX idx_task_priority ON task (priority);
CREATE INDEX idx_task_deadline ON task (deadline);

-- комбинированный (покрывает status, priority, deadline)
CREATE INDEX idx_task_status_priority_deadline
    ON task (status, priority, deadline);

-- partial (активные задачи), ускоряет поиск: просроченные, ближайшие задачи, LLM summary
CREATE INDEX idx_task_active
    ON task (deadline) WHERE status IN ('WAIT', 'WORK');

-- полнотекстовый поиск
CREATE INDEX idx_task_search
    ON task
    USING GIN (to_tsvector('simple', name || ' ' || coalesce (description, '')));
