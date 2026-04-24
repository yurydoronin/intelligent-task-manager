package task.manager.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = [
    "task.manager.input.adapters",
    "task.manager.application.ports",
    "task.manager.infrastructure",
])
@ConfigurationPropertiesScan
class TaskManagerApplication

fun main(args: Array<String>) {
    runApplication<TaskManagerApplication>(*args)
}
