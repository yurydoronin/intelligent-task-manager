package task.manager.application.ports.input.commands

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensureNotNull
import org.springframework.stereotype.Service
import task.manager.application.ports.output.TaskRepositoryPort
import task.manager.domain.model.task.TaskId
import task.manager.types.error.BusinessError

@Service
class DeleteTaskUseCaseImpl(
    private val taskRepo: TaskRepositoryPort
) : DeleteTaskUseCase {

    override fun execute(command: DeleteTaskCommand): Either<BusinessError, Unit> = either {
        val task = ensureNotNull(taskRepo.findById(TaskId(command.taskId))) {
            TaskError.NotFound
        }

        taskRepo.delete(task)
    }
}

sealed interface TaskError : BusinessError {
    data object NotFound : TaskError {
        override val message: String = "Задача не найдена"
    }
}