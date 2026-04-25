package task.manager.application.ports.input.commands

import arrow.core.Either
import java.time.Instant
import task.manager.domain.model.task.Priority
import task.manager.types.error.BusinessError

interface CreateTaskUseCase {
    fun execute(command: CreateTaskCommand): Either<BusinessError, Unit>
}

/**
 * (input DTO) Command to create a task
 */
data class CreateTaskCommand(
    val name: String,
    val description: String?,
    val priority: Priority,
    val deadline: Instant?,
)