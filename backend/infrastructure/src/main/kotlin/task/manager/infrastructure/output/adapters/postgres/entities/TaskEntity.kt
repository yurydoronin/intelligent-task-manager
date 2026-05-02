package task.manager.infrastructure.output.adapters.postgres.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "task")
class TaskEntity private constructor(
    @Id
    val id: UUID,
    val name: String,
    val description: String?,
    val priority: String,
    val status: String,
    @Column(name = "created_at")
    val createdAt: Instant,
    val deadline: Instant?,
    @Column(name = "completed_at")
    val completedAt: Instant?
) {
    companion object {
        fun of(
            id: UUID,
            name: String,
            description: String?,
            priority: String,
            status: String,
            createdAt: Instant,
            deadline: Instant?,
            completedAt: Instant?
        ): TaskEntity =
            TaskEntity(
                id = id,
                name = name,
                description = description,
                priority = priority,
                status = status,
                createdAt = createdAt,
                deadline = deadline,
                completedAt = completedAt
            )
    }
}
