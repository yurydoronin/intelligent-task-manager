package task.manager.application.ports.input.queries

import arrow.core.Either
import java.time.Instant
import task.manager.domain.model.task.Name
import task.manager.domain.model.task.Priority
import task.manager.domain.model.task.Status
import task.manager.domain.model.task.TaskId
import task.manager.types.error.BusinessError

interface GetTaskUseCase {
    fun execute(): Either<BusinessError, List<GetActiveTaskResult>>
}

/**
 * (output DTO) List of active tasks
 */
data class GetActiveTaskResult(
    val taskId: TaskId,
    val name: Name,
    val description: String?,
    val priority: Priority,
    val status: Status,
    val createdAt: Instant,
    val deadline: Instant?,
    val completedAt: Instant?
)