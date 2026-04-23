package task.manager.input.adapters.http

import java.time.Instant
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import task.manager.application.ports.input.queries.GetActiveTaskResult
import task.manager.application.ports.input.queries.GetTaskUseCase
import task.manager.domain.model.task.Name
import task.manager.domain.model.task.Priority
import task.manager.domain.model.task.Status

@RestController
@RequestMapping("/api/v1/task/active")
class GetActiveTaskController(
    private val useCase: GetTaskUseCase
) {
    @GetMapping
    fun get(): ResponseEntity<List<ActiveTaskResponse>> =
        useCase.execute()
            .fold(
                ifLeft = { ResponseEntity.status(HttpStatus.NOT_FOUND).build() },
                ifRight = { results -> ResponseEntity.ok(results.toResponse()) }
            )
}

/**
 * (DTO) HTTP-Response containing the list of active tasks
 */
fun List<GetActiveTaskResult>.toResponse(): List<ActiveTaskResponse> =
    map { it.toResponse() }

fun GetActiveTaskResult.toResponse(): ActiveTaskResponse =
    ActiveTaskResponse(
        id = taskId.id,
        name = name,
        description = description,
        priority = priority,
        status = status,
        createdAt = createdAt,
        deadline = deadline,
        completedAt = completedAt,
    )

data class ActiveTaskResponse(
    val id: UUID,
    val name: Name,
    val description: String?,
    val priority: Priority,
    val status: Status,
    val createdAt: Instant,
    val deadline: Instant?,
    val completedAt: Instant?
)
