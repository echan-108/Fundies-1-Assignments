import kotlin.random.Random

// QUESTION 1
fun readLnSarcastically(): String {
    val input = readln()

    val result =
            StringBuilder().let {
                for (i in 0 until input.length) {
                    val randomNumber = Random.nextDouble(0.0, 1.0)
                    if (randomNumber > .5) {
                        it.append(input[i].uppercase())
                    } else {
                        it.append(input[i].lowercase())
                    }
                }
                it
            }

    return result.toString()
}

fun main() {
    println("input something?")
    println(readLnSarcastically())
}
