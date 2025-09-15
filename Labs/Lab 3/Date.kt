fun main() {
    // QUESTION 1
    val myBirthday = Date(7, 17, 2006)
    println(myBirthday)
}


data class Date(val month: Int, val day: Int, val year: Int) {
    // would use an array, but I don't know how those work in kotlin
    private val dateString = when (month) {
        1 -> {
            "January"
        }

        2 -> {
            "Febuary"
        }

        3 -> {
            "March"
        }

        4 -> {
            "April"
        }

        5 -> {
            "May"
        }

        6 -> {
            "June"
        }

        7 -> {
            "July"
        }

        8 -> {
            "August"
        }

        9 -> {
            "September"
        }

        10 -> {
            "October"
        }

        11 -> {
            "November"
        }

        12 -> {
            "December"
        }

        else -> {
            "bad month number"
        }
    }

    // QUESTION 2
    override fun toString(): String = "$dateString / $day / $year"

    // QUESTION 3
    /**
     * adds [otherDate] to date
     * [otherDate] is read as number of days, months, years
     * the parameter of Date(1,2,3) is read as add 1 month, 2 days, and 3 years,
     */
    fun addDate(otherDate: Date): Date {
        var resultMonth = 0
        var resultDay = 0
        var resultYear = 0

        var daySum = (this.day + other.day)
        var monthSum = (this.month + other.month)
        var yearSum = (this.year + other.year)

        if (daySum > 30) {
            monthSum++
            resultDay = daySum % 30
        }

        if (monthSum > 12) {
            yearSum++
            resultMonth = monthSum % 12
        }

        resultYear = yearSum;

        if (resultMonth == 0) {
            resultMonth = 12
        }

        if (resultDay == 0) {
            resultDay = 30
        }

        return Date(resultMonth, resultDay, resultYear)
    }
}

// QUESTION 4
/**
 * Asks user to input a month day and year and creates a date object with the information
 */
fun createDate(): Date {
    println("Ready to log a date?")
    println("Please enter a month in number format [1-12]: ")
    val month = readln().toInt()
    println("Please enter a day in number format [1-30]: ")
    val day = readln().toInt()
    println("Please enter a year in number format: ")
    val year = readln().toInt()
    return Date(month, day, year)
}

// QUESTION 5
// No Attempt as that would require an entire code refactor
// I would create a month enum with two parameters one for the number of days in the month, and one for the number assigned to the month
// I would change the date class to accept a month enum paramater for the month, and refactor as follows
// I would also change the createDate() function so that it asks the user to input string for the month, use a switch case statement to match it to a date enum, and pass the enum to the Date() constructor
