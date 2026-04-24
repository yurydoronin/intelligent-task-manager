package task.manager.domain.model.task

@JvmInline
value class Name(
    val value: String
) {
    companion object {
        fun of(value: String): Name {
            require(value.isNotBlank()) { "Название задачи не должно быть пустым" }
            return Name(value)
        }
    }
}
