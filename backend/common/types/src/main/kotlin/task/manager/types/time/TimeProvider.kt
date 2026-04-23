package task.manager.types.time

import java.time.Instant

interface TimeProvider {
    fun now(): Instant
}