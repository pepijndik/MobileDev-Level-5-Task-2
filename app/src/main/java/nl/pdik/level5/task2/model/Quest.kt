package nl.pdik.level5.task2.model

data class Quest(
    val choices: ArrayList<String>,
    val correctAnswer: String,
    val id: Long,
    val question: String

)
