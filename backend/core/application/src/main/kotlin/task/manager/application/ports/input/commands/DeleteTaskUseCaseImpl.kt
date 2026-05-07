package task.manager.application.ports.input.commands

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import org.springframework.stereotype.Service
import task.manager.application.ports.output.persistence.DeleteTaskRepositoryPort
import task.manager.application.ports.output.persistence.LoadTaskRepositoryPort
import task.manager.domain.model.task.TaskError
import task.manager.domain.model.task.TaskId
import task.manager.types.error.BusinessError

@Service
class DeleteTaskUseCaseImpl(
    private val loadTaskPort: LoadTaskRepositoryPort,
    private val deleteTaskPort: DeleteTaskRepositoryPort
) : DeleteTaskUseCase {

    override fun execute(command: DeleteTaskCommand): Either<BusinessError, Unit> = either {
        val task = ensureNotNull(loadTaskPort.findById(TaskId(command.taskId))) {
            TaskError.TaskNotFound
        }

        deleteTaskPort.delete(task)
    }
}
