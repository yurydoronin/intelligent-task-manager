package task.manager.application.ports.input.commands

import arrow.core.Either
import java.time.Instant
import java.util.UUID
import task.manager.domain.model.task.Priority
import task.manager.domain.model.task.Status
import task.manager.types.error.BusinessError

interface UpdateTaskUseCase {
    fun execute(command: UpdateTaskCommand): Either<BusinessError, Unit>
}

/**
 * (input DTO) Command to update a task
 */
data class UpdateTaskCommand(
    val taskId: UUID,
    val description: String?,
    val priority: Priority?,
    val status: Status?,
    val deadline: Instant?,
)