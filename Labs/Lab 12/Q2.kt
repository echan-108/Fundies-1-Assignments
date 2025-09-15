// QUESTION 2

class Diary(private val password: String) {

    private val contents = mutableListOf<String>()

    /** Adds the [entry] to the diary with the current date, if the [password] is correct. */
    fun addEntry(pwd: String, entry: String) {
        if (pwd == password) {
            contents.add(
                    "$entry : ${java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"))}"
            )
        }
    }

    fun startDiary(pwd: String): Diary {
        return Diary(pwd).apply {
            var input = ""
            while (input != "quit") {
                println("Enter new entry or type quit to exit")
                input = readln()
                addEntry(pwd, input)
            }
        }
    }

    override fun toString(): String {
        return contents.toString()
    }
}

fun main() {
    var diary = Diary("BOING")
    diary = diary.startDiary("BOING")
    println(diary)
}
