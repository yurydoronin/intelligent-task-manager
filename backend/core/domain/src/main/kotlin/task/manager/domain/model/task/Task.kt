package task.manager.domain.model.task

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import java.time.Instant
import task.manager.types.error.BusinessError
import task.manager.types.time.TimeProvider

/**
 * Корень агрегата task.
 */
class Task private constructor(
    val id: TaskId,
    val name: Name,
    val description: String?,
    val priority: Priority,
    val status: Status,
    val createdAt: Instant,
    /**
     * Дедлайн, к которому задача должна быть выполнена.
     */
    val deadline: Instant?,
    /**
     * Фактическое время завершения задачи.
     */
    val completedAt: Instant?
) {
    companion object {
        fun of(
            name: Name,
            description: String?,
            priority: Priority,
            deadline: Instant?,
            now: Instant
        ): Either<TaskError, Task> = either {

            deadline?.let {
                ensure(it.isAfter(now)) { TaskError.InvalidDeadline }
            }

            Task(
                id = TaskId.of(),
                name = name,
                description = description,
                priority = priority,
                status = Status.WAIT,
                createdAt = now,
                deadline = deadline,
                completedAt = null
            )
        }

        fun restore(
            id: TaskId,
            name: Name,
            description: String?,
            priority: Priority,
            status: Status,
            createdAt: Instant,
            deadline: Instant?,
            completedAt: Instant?
        ): Task =
            Task(
                id = id,
                name = name,
                description = description,
                priority = priority,
                status = status,
                createdAt = createdAt,
                deadline = deadline,
                completedAt = completedAt
            )
    }

    fun start(): Either<TaskError, Task> = either {
        ensure(status == Status.WAIT) { TaskError.InvalidStatus }

        Task(
            id = id,
            name = name,
            description = description,
            priority = priority,
            status = Status.WORK,
            createdAt = createdAt,
            deadline = deadline,
            completedAt = completedAt
        )
    }

    fun markDone(timeProvider: TimeProvider): Either<TaskError, Task> = either {
        ensure(status != Status.WORK) { TaskError.InvalidStatus }

        val now = timeProvider.now()

        Task(
            id = id,
            name = name,
            description = description,
            priority = priority,
            status = Status.DONE,
            createdAt = createdAt,
            deadline = deadline,
            completedAt = now
        )
    }

    fun patch(
        description: String?,
        priority: Priority?,
        status: Status?,
        deadline: Instant?,
        now: Instant
    ): Either<TaskError, Task> = either {

        deadline?.let {
            ensure(it.isAfter(now)) { TaskError.InvalidDeadline }
        }

        Task(
            id = id,
            name = name,
            description = description ?: this@Task.description,
            priority = priority ?: this@Task.priority,
            status = status ?: this@Task.status,
            createdAt = createdAt,
            deadline = deadline ?: this@Task.deadline,
            completedAt = completedAt
        )
    }

}

sealed class TaskError(override val message: String) : BusinessError {
    data object InvalidDeadline : TaskError("Срок выполнения задачи должен быть в будущем")
    data object InvalidStatus : TaskError("Недопустимый статус задачи")
    data object TaskNotFound : TaskError("Задача не найдена")
}
