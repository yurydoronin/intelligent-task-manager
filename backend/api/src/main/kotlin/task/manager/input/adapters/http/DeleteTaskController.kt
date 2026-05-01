package task.manager.input.adapters.http

import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import task.manager.application.ports.input.commands.DeleteTaskCommand
import task.manager.application.ports.input.commands.DeleteTaskUseCase
import task.manager.domain.model.task.TaskError

@RestController
@RequestMapping("/api/v1/task")
class DeleteTaskController(
    private val useCase: DeleteTaskUseCase
) {
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<String> =
        useCase.execute(DeleteTaskCommand(taskId = id))
            .fold(
                ifLeft = { error ->
                    when (error) {
                        is TaskError.TaskNotFound -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.message)
                        else -> ResponseEntity.badRequest().body(error.message)
                    }
                },
                ifRight = {
                    ResponseEntity.noContent().build()
                }
            )
}
