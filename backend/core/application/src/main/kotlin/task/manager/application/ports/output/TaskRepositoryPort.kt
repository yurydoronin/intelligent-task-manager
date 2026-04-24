package task.manager.application.ports.output

import task.manager.domain.model.task.Task

interface TaskRepositoryPort {
    fun save(task: Task)
}
