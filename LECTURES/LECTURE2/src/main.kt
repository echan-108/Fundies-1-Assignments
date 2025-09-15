import kotlin.random.Random

/** Returns a list of [count] random integers in the range 1-10 (inclusive). */
fun getRandomNumbers(count: Int): List<Int> {
    // keep types capital
    val nums: MutableList<Int> = mutableListOf()
    for (i in 1..count) {
        val numToAdd = Random.nextInt(1, 11)
        nums.add(numToAdd)
    }
    return nums
}

/** Returns the count of even numbers in [nums]. */
fun getEvenCount(nums: List<Int>): Int {
    var count = 0
    for (num in nums) {
        if (num % 2 == 0) {
            count++
        }
    }
    return count
}

/** Returns how many tries it took to get a list with all evens (10 numbers) */
fun getTriesToGetAllEvens(): Int {
    var tries = 0
    while (true) {
        val nums = getRandomNumbers(10)
        tries++
        if (getEvenCount(nums) == 10) {
            return tries
        }
    }
}

/** Prints all the elements in [nums]. */
fun printNums(nums: List<Int>) {
    print("The numbers are: ")
    for (num in nums) print("$num ")
    println()
}

fun main() {
    val randomNums = getRandomNumbers(10)
    printNums(randomNums)
    val numEven = getEvenCount(randomNums)
    println("$numEven of the ${randomNums.size} were even.")
    val tries = getTriesToGetAllEvens()
    println("It took $tries tries to get all evens.")
}
