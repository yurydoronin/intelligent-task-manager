package task.manager.domain.model.task

import com.github.f4b6a3.uuid.UuidCreator
import java.util.UUID

@JvmInline
value class TaskId(
    val id: UUID
) {
    companion object {
        fun of() = TaskId(UuidCreator.getTimeOrderedEpoch()) // UUIDv7
    }
}
