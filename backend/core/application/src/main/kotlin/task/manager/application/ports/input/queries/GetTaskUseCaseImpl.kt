package task.manager.application.ports.input.queries

import java.util.UUID
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import task.manager.domain.model.task.Name
import task.manager.domain.model.task.Priority
import task.manager.domain.model.task.Status
import task.manager.domain.model.task.TaskId

@Service
class GetTaskUseCaseImpl(
    private val jdbcTemplate: JdbcTemplate
) : GetTaskUseCase {

    @Transactional(readOnly = true)
    override fun execute(): List<GetActiveTaskResult> {
        val sql = """
        SELECT t.id, t.name, t.description, t.priority, t.status, t.created_at, t.deadline, t.completed_at
        FROM task t
    """.trimIndent()

        val mapper = RowMapper { rs, _ ->
            GetActiveTaskResult(
                taskId = TaskId(UUID.fromString(rs.getString("id"))),
                name = Name(rs.getString("name")),
                description = rs.getString("description"),
                priority = Priority.valueOf(rs.getString("priority")),
                status = Status.valueOf(rs.getString("status")),
                createdAt = rs.getTimestamp("created_at").toInstant(),
                deadline = rs.getTimestamp("deadline")?.toInstant(),
                completedAt = rs.getTimestamp("completed_at")?.toInstant()
            )
        }

        return jdbcTemplate.query(sql, mapper)
    }
}
