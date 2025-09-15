// Question 1
fun roomSize(hallway: Array<Boolean>, roomNumber: Int): Int {
    // start with an accumulator variable,
    //  and an variable to keep track if the room has been visited
    // once an empty room is hit, if the visited variable is true, return the accumulator
    // if not keep going until the end of the list

    var result = 0
    var visited = false
    for (i in 0 until hallway.size) {
        if (hallway[i]) {
            result++
        } else {
            if (visited) {
                return result
            }
        }
        if (i == roomNumber) {
            visited = true
        }
    }

    return result
}

// Question 2
fun length(text: String): Int {
    if (text.isEmpty()) {
        return 0
    } else {
        return 1 + length(text.substring(1))
    }
}

fun length(text: String, index: Int): Int {
    if (index == text.length) {
        return 0
    } else {
        return 1 + length(text, index + 1)
    }
}

// Question 3
fun isPalindrome(text: String): Boolean {
    if (text.length <= 1) {
        println(text)
        return true
    } else if (text.first() == text.last()) {
        println(text)
        return isPalindrome(text.substring(1, text.length - 1))
    } else {
        println(text)
        return false
    }
}

// use recursive reverse  function (cheating)
// use recursive two pointer solution ??!!
// is this even possible?? // Don't I need the index variable
// NVM got it

// Question 4
fun wrapParens(text: String, num: Int): String {
    if (num == 0) {
        return text
    } else {
        return "(${wrapParens(text, num - 1)})"
    }
}

// Question 5
fun powerset(inp: Set<int>) {
    // get every n length subset at current depth using iteration and conversion to an array
    // print this subset out
    // call the powerset function on every n-1 length chunk of this subset
    // sum all of these sets together
    // if length is zero, print empty set !!!

    if (n == 0) {
        println("[ ]")
    }
}
// ill come back to this later, but I won't finish it because I want to start the homework when the
// help is avialible to me

fun main() {
    val sample = arrayOf(true, true, true, true, false, true, false, true)
    println(roomSize(sample, 2))
    println(isPalindrome("tacocat"))
    println(isPalindrome("notapalindrome"))
    println(length("Hello World!"))
    println(wrapParens("cat", 12))
}
