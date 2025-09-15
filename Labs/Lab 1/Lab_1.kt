// QUESTION 1
/**
 *  Converts from degrees Fahrenheit to degrees Celsius.
 *
 *  @return [fahrenheit] converted from degrees Fahrenheit to degrees Celsius.
 */
fun fahrenheitToCelsius(fahrenheit: Int): Int {
    return (fahrenheit - 32) * 5 / 9
}
// Changed the order of operations using parenthesis to solve the question;

// QUESTION 2
/**
 *  Calculates the income tax of a person in califorina who makes between $11,001 and $24,600.
 *
 *  @return the income tax of a person in califorina who makes between $11,001 and $24,600.
 */
fun incomeTax(income: Int): Double {
    if (income > 10412 && income < 11001) {
        val chunk1 = income - 10412
        return = 104.12 + (chunk1 * 0.02)
    } else if (income > 11001) {
        val chunk1 = 11001 - 10412
        val chunk2 = income - 11001
        return 104.12 + (chunk1 * 0.02) + 1,100.10 + (11001 + .12)
    }
}

// QUESTION 3
/**
 * Prints the income tax of a person in califorina who makes between $11,001 and $24,600.
 *
 *  @return the income tax of a person in califorina who makes between $11,001 and $24,600.
 */
fun printIncomeTax(income: Int) {
    val taxOwed = incomeTax(income)
    val takeHome = income - taxOwed
    println("Your income is ${income} before tax and ${takeHome} after tax. You pay ${taxOwed} income tax.")
}

// QUESTION4
/**
 * Asks the user for their income and prints it using the printIncomeTax function.
 */

fun printIncomeTaxUserInput() {
    println("Enter a number between 11,001 and 20,600")
    // TA Hanna helped me out with the casting
    val income = readLine()!!.toInt()// ?. !!.
    if (income is Int) {
        printIncomeTax(income)
    } else {
        println("please type in an integer between 10412 and 20600")
    }
}

import kotlin.random
/**
 * Generates a random number, and passes that to the printIncomeTax function
 */
fun printIncomeTaxRandom() {
    // Import Random Thing at Top (would put at the top of a real code file)
    // Reference Random Library to generate Number with a range
    val randomIncome = Random.nextInt( 10_001, 24_601) // one at the end because of nextInt range semantics
    printIncomeTax(randomIncome);
}

// QUESTION 5
fun printIntelliJStatus() {
    var intelliJStatus = "IntelliJ installation has been fairly easy for me. I was able to set it up in the manner requried of me"
    print(intelliJStatus)
}