package task.manager.infrastructure.output.adapters.postgres

import org.springframework.stereotype.Repository
import task.manager.application.ports.output.TaskRepositoryPort
import task.manager.domain.model.task.Name
import task.manager.domain.model.task.Priority
import task.manager.domain.model.task.Status
import task.manager.domain.model.task.Task
import task.manager.domain.model.task.TaskId
import task.manager.infrastructure.output.adapters.postgres.entities.TaskEntity

@Repository
class TaskRepository(
    private val repo: TaskJpaRepository
) : TaskRepositoryPort {

    override fun save(task: Task) {
        repo.save(task.toEntity())
    }
}

fun Task.toEntity(): TaskEntity =
    TaskEntity(
        id = id.id,
        name = name.value,
        description = description,
        priority = priority.name,
        status = status.name,
        createdAt = createdAt,
        deadline = deadline,
        completedAt = completedAt
    )

fun TaskEntity.toDomain(): Task =
    Task.restore(
        id = TaskId(id),
        name = Name.of(name),
        description = description,
        priority = Priority.valueOf(priority),
        status = Status.valueOf(status),
        createdAt = createdAt,
        deadline = deadline,
        completedAt = completedAt
    )
