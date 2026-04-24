package task.manager.infrastructure.output.adapters.postgres

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import task.manager.infrastructure.output.adapters.postgres.entities.TaskEntity

interface TaskJpaRepository : JpaRepository<TaskEntity, UUID>
