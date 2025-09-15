package `Lab 2`

// QUESTION 2
fun getFinalGrade(hwAvg: Double, quizExamAvg: Double, labAvg: Double, lectureAvg: Double): String {
    val hwChunk = hwAvg * .45
    val quizExamChunk = quizExamAvg * .40
    val labChunk = labAvg * .1
    val lectureChunk = lectureAvg * .05
    val numGrade = hwChunk + quizExamChunk + labChunk + lectureChunk;

    return when (numGrade) {
        in 0..59 -> {
            "F"
        }

        in 60..62 -> {
            "D-"
        }

        in 63..66 -> {
            "D"
        }

        in 67..69 -> {
            "D+"
        }

        in 70..72 -> {
            "C-"
        }

        in 73..76 -> {
            "C"
        }

        in 77..79 -> {
            "C+"
        }

        in 80..82 -> {
            "B-"
        }

        in 83..86 -> {
            "B"
        }

        in 87..89 -> {
            "B+"
        }

        in 90..93 -> {
            "A-"
        }

        else -> {
            "A"
        }
    }
}
