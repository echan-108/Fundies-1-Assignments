
fun main() {
    println("Hello, World!")
    val char = "Hello, World!"[11]
    println("$char")
    println("Hello, World!".length)

    // for loop syntax
    for (i in 0..12) {
        println("Hello, World!"[i])
    }

    // while loop syntax
    while (true) {
        println("Hello, World!")
        break
    }

    // validatinon input syntax
    var counter = 0
    var input = 0;
    println("Enter a number between 1 and 10")
    while (input !in 1..10 ) {
        input = readln().toInt()
        if (input in 1..10) {
            println("The user entered $input")
        } else {
            print("Please enter a number between 1 and 10")
            for (i in 0..counter){
                print("!")
            }
            counter++
            println()
        }
    }

    // String Iteration Syntax
    val s = "Kotlin"
    println(s.length) // 6
    println(s.indices) // 0..5
    println(s[0]) // 'K'
    println(s[s.length - 1]) // 'n'
    println(s.lowercase()) // kotlin

    // String Palindrome Iteration Function
    fun inPalandrome(str: String): Boolean {
        for (i in 0..(str.length /2)) {
            var pointer1 = i
            var pointer2 = str.length - 1 - i
            if (str[pointer1] != str[pointer2]) {
                return false
            }
        }
        return true
    }

    // String Palindrome Recursive Function
    fun inPalindromeRecursive(str: String, index: Int): Boolean {
        if (index >= str.length / 2) {
            return true
        }
        if (str[index] != str[str.length - 1 - index]) {
            return false
        }
        return inPalindromeRecursive(str, index + 1)
    }

    // Printing Tests
    println(inPalandrome("racecar")) // true
    println(inPalandrome("hello")) // false
    println(inPalindromeRecursive("hannah", 0)) // true

    
}