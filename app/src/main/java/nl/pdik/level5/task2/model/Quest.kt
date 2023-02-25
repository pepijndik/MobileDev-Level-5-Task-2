package nl.pdik.level5.task2.model

data class Quest(
    val choices: Array<String>,
    val correctAnswer: String,
    val id: Int,
    val question: String

)
