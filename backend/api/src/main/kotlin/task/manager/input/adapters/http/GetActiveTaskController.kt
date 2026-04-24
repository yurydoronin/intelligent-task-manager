package task.manager.input.adapters.http

import java.time.Instant
import java.util.UUID
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
    fun get(): List<ActiveTaskResponse> = useCase.execute().toResponse()
}

/**
 * (DTO) HTTP-Response containing the list of active tasks
 */
fun List<GetActiveTaskResult>.toResponse(): List<ActiveTaskResponse> =
    map { it.toResponse() }

fun GetActiveTaskResult.toResponse(): ActiveTaskResponse =
    ActiveTaskResponse(
        id = taskId.id,
        name = name.value,
        description = description,
        priority = priority.name,
        status = status.name,
        createdAt = createdAt,
        deadline = deadline,
        completedAt = completedAt,
    )

data class ActiveTaskResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val priority: String,
    val status: String,
    val createdAt: Instant,
    val deadline: Instant?,
    val completedAt: Instant?
)
