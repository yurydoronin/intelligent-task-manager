package task.manager.input.adapters.http

import java.time.Instant
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import task.manager.application.ports.input.commands.UpdateTaskCommand
import task.manager.application.ports.input.commands.UpdateTaskUseCase
import task.manager.domain.model.task.Priority
import task.manager.domain.model.task.Status
import task.manager.domain.model.task.TaskError

@RestController
@RequestMapping("/api/v1/task")
class UpdateTaskController(
    private val useCase: UpdateTaskUseCase
) {
    @PatchMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: UpdateTaskRequest
    ): ResponseEntity<String> =
        useCase.execute(request.toCommand(taskId = id))
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

/**
 * (DTO) HTTP-Request to update a task
 */
data class UpdateTaskRequest(
    val description: String? = null,
    val priority: Priority? = null,
    val status: Status? = null,
    val deadline: Instant? = null
)

fun UpdateTaskRequest.toCommand(taskId: UUID): UpdateTaskCommand =
    UpdateTaskCommand(
        taskId = taskId,
        description = description,
        priority = priority,
        status = status,
        deadline = deadline
    )
