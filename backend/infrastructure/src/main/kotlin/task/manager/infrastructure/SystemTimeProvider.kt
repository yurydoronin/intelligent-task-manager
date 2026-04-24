package task.manager.infrastructure

import java.time.Clock
import java.time.Instant
import org.springframework.stereotype.Component
import task.manager.types.time.TimeProvider

/**
 * Источник текущего времени (UTC).
 */
@Component
class SystemTimeProvider(
    private val clock: Clock = Clock.systemUTC()
) : TimeProvider {
    override fun now(): Instant = clock.instant()
}
