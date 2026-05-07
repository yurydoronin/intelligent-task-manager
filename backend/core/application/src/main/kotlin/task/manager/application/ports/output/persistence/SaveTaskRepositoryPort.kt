package task.manager.application.ports.output.persistence

import task.manager.domain.model.task.Task

interface SaveTaskRepositoryPort {
    fun save(task: Task)
}
