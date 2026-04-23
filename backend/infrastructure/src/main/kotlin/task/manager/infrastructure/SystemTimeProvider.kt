package task.manager.infrastructure

import java.time.Clock
import java.time.Instant
import task.manager.types.time.TimeProvider

/**
 * Источник текущего времени (UTC).
 */
class SystemTimeProvider(
    private val clock: Clock = Clock.systemUTC()
) : TimeProvider {
    override fun now(): Instant = clock.instant()
}