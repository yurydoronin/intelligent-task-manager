package task.manager.application.ports.output.persistence

import task.manager.domain.model.task.Task

interface DeleteTaskRepositoryPort {
    fun delete(task: Task)
}
