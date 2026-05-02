package task.manager.input.adapters.http

import java.time.Instant
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import task.manager.application.ports.input.commands.CreateTaskCommand
import task.manager.application.ports.input.commands.CreateTaskUseCase
import task.manager.domain.model.task.Name
import task.manager.domain.model.task.Priority

@RestController
@RequestMapping("/api/v1/task")
class CreateTaskController(
    private val useCase: CreateTaskUseCase
) {
    @PostMapping
    fun create(@RequestBody request: CreateTaskRequest): ResponseEntity<String> =
        useCase.execute(request.toCommand())
            .fold(
                ifLeft = { error ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.message)
                },
                ifRight = {
                    ResponseEntity.status(HttpStatus.CREATED).build()
                }
            )
}

/**
 * (DTO) HTTP-Request to create a task
 */
data class CreateTaskRequest(
    val name: String,
    val description: String?,
    val priority: Priority,
    val deadline: Instant?
)

private fun CreateTaskRequest.toCommand(): CreateTaskCommand =
    CreateTaskCommand(
        name = name,
        description = description,
        priority = priority,
        deadline = deadline,
    )
