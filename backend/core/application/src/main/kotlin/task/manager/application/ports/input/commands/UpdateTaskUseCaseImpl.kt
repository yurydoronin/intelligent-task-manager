package task.manager.application.ports.input.commands

import arrow.core.Either
import arrow.core.raise.either
import org.springframework.stereotype.Service
import task.manager.application.ports.output.persistence.LoadTaskRepositoryPort
import task.manager.application.ports.output.persistence.SaveTaskRepositoryPort
import task.manager.domain.model.task.TaskError
import task.manager.domain.model.task.TaskId
import task.manager.types.error.BusinessError
import task.manager.types.time.TimeProvider

@Service
class UpdateTaskUseCaseImpl(
    private val loadTaskPort: LoadTaskRepositoryPort,
    private val saveTaskPort: SaveTaskRepositoryPort,
    private val timeProvider: TimeProvider,
) : UpdateTaskUseCase {

    override fun execute(command: UpdateTaskCommand): Either<BusinessError, Unit> = either {
        val existingTask = loadTaskPort.findById(TaskId(command.taskId))
            ?: raise(TaskError.TaskNotFound)

        val updatedTask = existingTask.patch(
            description = command.description,
            priority = command.priority,
            status = command.status,
            deadline = command.deadline,
            now = timeProvider.now()
        ).bind()

        saveTaskPort.save(updatedTask)
    }
}
