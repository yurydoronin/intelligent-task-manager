package task.manager.application.ports.output

import task.manager.domain.model.task.Task
import task.manager.domain.model.task.TaskId

interface TaskRepositoryPort {
    fun save(task: Task)
    fun delete(task: Task)
    fun findById(id: TaskId): Task?
}
