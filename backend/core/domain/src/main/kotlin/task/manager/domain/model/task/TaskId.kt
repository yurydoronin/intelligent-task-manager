package task.manager.domain.model.task

import java.util.UUID

@JvmInline
value class TaskId(
    val id: UUID
) {
    companion object {
        fun of() = TaskId(UUID.randomUUID())
    }
}
