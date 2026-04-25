package task.manager.application.ports.input.commands

import arrow.core.Either
import java.util.UUID
import task.manager.domain.model.task.TaskId
import task.manager.types.error.BusinessError

interface DeleteTaskUseCase {
    fun execute(command: DeleteTaskCommand): Either<BusinessError, Unit>
}

/**
 * (input DTO) Command to remove the task
 */
data class DeleteTaskCommand(val taskId: UUID)