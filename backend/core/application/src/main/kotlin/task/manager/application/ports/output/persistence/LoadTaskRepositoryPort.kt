package task.manager.application.ports.output.persistence

import task.manager.domain.model.task.Task
import task.manager.domain.model.task.TaskId

interface LoadTaskRepositoryPort {
    fun findById(id: TaskId): Task?
}
