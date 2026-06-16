package task.manager.input.adapters.http

import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<String> {
        log.warn("Validation error: {}", ex.message)

        return ResponseEntity
            .badRequest()
            .body(ex.message ?: "Invalid input")
    }

    @ExceptionHandler(OptimisticLockingFailureException::class)
    fun handleOptimisticLock(ex: OptimisticLockingFailureException): ResponseEntity<String> {
        log.warn("Optimistic lock failure: {}", ex.message)

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body("Conflict: concurrent modification detected")
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpected(ex: Exception): ResponseEntity<String> {
        log.error("Unhandled error: {}", ex.message, ex)

        return ResponseEntity
            .internalServerError()
            .body("Unexpected server error: ${ex.cause}")
    }
}
