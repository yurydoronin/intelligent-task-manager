package task.manager.application.ports.input.commands

import arrow.core.Either
import arrow.core.raise.either
import org.springframework.stereotype.Service
import task.manager.application.ports.output.TaskRepositoryPort
import task.manager.domain.model.task.Task
import task.manager.types.error.BusinessError
import task.manager.types.time.TimeProvider

@Service
class CreateTaskUseCaseImpl(
    private val taskRepo: TaskRepositoryPort,
    private val timeProvider: TimeProvider,
) : CreateTaskUseCase {

    override fun execute(command: TaskCreationCommand): Either<BusinessError, Unit> = either {
        val task = Task.of(
            name = command.name,
            description = command.description,
            priority = command.priority,
            deadline = command.deadline,
            now = timeProvider.now()
        ).bind()

        taskRepo.save(task)
    }
}