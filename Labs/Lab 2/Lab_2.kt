package `Lab 2`// QUESTION 1
// got range statements from intellij suggestions


fun totalIncomeTax(income: Int): Double {
    val calTax = caliIncomeTax(income)
    val fedTax = fedIncomeTax(income)
    return calTax + fedTax
}

fun caliIncomTax(income: Int): Double {
    when (income) {
        in 0..10412 -> {
            return income * .01
        }

        in 10413..24684 -> {
            return 104.12 + (.02 * (income - 10413))
        }

        in 24685..38959 -> {
            return 389.56 + (.04 * (income - 24685))
        }

        in 38960..54081 -> {
            return 960.56 + (.06 * (income - 38960))
        }

        in 54082..68350 -> {
            return 1867.88 + (.08 * (income - 54082))
        }

        in 68351..349137 -> {
            return 3009.40 + (.093 * (income - 68351))
        }

        in 349138..418961 -> {
            return 29122.59 + (.103 * (income - 349138))
        }

        in 418962..698271 -> {
            return 36314.46 + (.113 * (income - 418962))
        }

        else -> {
            return 67876.49 + (.123 * (income - 698271))
        }
    }
}

fun fedIncomeTax(income: Int): Double {
    when (income) {
        in 0..11000 -> {
            return income * .1
        }

        in 11001..44725 -> {
            return 1100 + ((income - 11011) * .12)
        }

        in 44726..95375 -> {
            return 5146.88 + ((income - 44726) * .22)
        }

        in 95376..182100 -> {
            return 16289.66 + ((income - 95376) * .24)
        }

        in 182101..231250 -> {
            return 37103.42 + ((income - 182101) * .32)
        }

        in 231251..578125 -> {
            return 52831.1 + ((income - 231251) * .35)
        }

        else -> {
            return 174237 + ((income - 578125) * .37)
        }
    }
}

// QUESTION 2
fun getFinalGrade(hwAvg: Double, quizExamAvg: Double, labAvg: Double, lectureAvg: Double): String {
    val hwChunk = hwAvg * .45
    val quizExamChunk = quizExamAvg * .40
    val labChunk = labAvg * .1
    val lectureChunk = lectureAvg * .05
    val numGrade = hwChunk + quizExamChunk + labChunk  + lectureChunk;

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

println(getFinalGrade(100, 83, 99, 82))

// QUESTION 3
enum class BookCharacter(val catchPhrase: String) {
    CHARLIE("oh no"),
    WILLYWONKA("I eat chocolate"),
    VERUSIKA("I am a blueberry"),
    GRANDPAJOE("I got out of bed for this")
}

// QUESTION 4
data class sandwhich(val bread: Bread, val toppingCount: Int, val toasted: Boolean)

enum class Bread(val name: String){
    WHEAT("wheat"),
    PUMPERNICKLE("pumpernickel"),
    WHITE("white"),
    RYE("rye"),
    SOURDOUGH("sourdough"),
    ENGLISHMUFFIN("english muffin")
}

val grilledCheese = sandwhich(Bread.WHITE, 1, True)
val turkeyClub = sandwhich(Bread.RYE, 7, False)

// QUESTION 5

