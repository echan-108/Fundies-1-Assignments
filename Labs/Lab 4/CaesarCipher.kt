// QUESTION 1
/**
 * Takes a string [message] and returns true if all the characters in the string are alphabetical
 * returns false otherwise
 */
fun allAlpha(message: String): Boolean {
    for (c in message) {
        if (!c.isLetter()) {
            return false
        }
    }
    return true
}

// QUESTION 2
/** Prompts the user to input a message that is all alphabetical characters returns the message */
fun userMessage(): String {
    var message: String
    do {
        println("Please input a message that is in all alphabetical characters")
        message = readln()
    } while (!allAlpha(message))
    return message
}

// QUESTION 3
/**
 * Taks a string [message] and an integer [shift] uses the shift to encode the message according to
 * the caesar cipher spec/algorithm returns the encoded message
 */
fun encode(message: String, shift: Int): String {

    // TODO : CLEAN THIS MESS UP
    var result = StringBuilder()
    for (c in message) {
        val base = if (c.isUpperCase()) 'A' else 'a'

        val shiftFactor =
                if (shift < 0) {
                    (c - base + 26 + shift) % 26
                } else {
                    (c - base + shift) % 26
                }
        val shiftedChar = (base + shiftFactor)
        result.append(shiftedChar)
    }
    return result.toString()
}

// QUESTION 4
/**
 * Takes a string [message] and an integer [originalRotation] and decodes the message according to
 * the caesar cipher spec/algorithm returns the decoded message
 */
fun decode(message: String, originalRotation: Int): String {
    return encode(message, -originalRotation)
}

// QUESTION 5
fun testAllAlpha() {
    assertFalse(allAlpha("asdfasdeaefafeal;ksdjf;lakejsf"))
    assertFalse(allAlpha("who is warren olney"))
    assertTrue(allAlpha("abcdefghijklmnopqrstuvwxyz"))
    println("All Tests Pass for allAlpha()")
}

fun testEncode() {
    val testingString = "abcdefghijklmnopqrstuvwxyz"
    assertEquals("bcdefghijklmnopqrstuvwxyza", encode(testingString, 1))
    assertEquals("zabcdefghijklmnopqrstuvwxy", encode(testingString, -1))
    assertEquals(testingString, encode(testingString, 0))
    println("All Tests Pass for encode()")
}

fun testDecode() {
    assertEquals("originalstring", decode(encode("originalstring", 12), 12))
    assertEquals("nonoriginalstring", decode(encode("nonoriginalstring", -12), -12))
    println("All Tests Pass for decode()")
}

//
fun main() {
    val testingString = "abcdefghijklmnopqrstuvwxyz"
    println(testingString + " : " + encode(testingString, 1))
    println(testingString + " : " + decode(encode(testingString, 13), 13))

    testAllAlpha()
    testEncode()
    testDecode()
}

// functions used for testing
fun assertEquals(
        expected: Any,
        actual: Any,
) {
    if (expected != actual) {
        throw AssertionError("Expected $expected, got $actual")
    }
}

/**
 * Verifies that [actual] is true.
 *
 * @throws AssertionError if it is false
 */
fun assertTrue(actual: Any) {
    assertEquals(true, actual)
}

/**
 * Verifies that [actual] is false.
 *
 * @throws AssertionError if it is true
 */
fun assertFalse(actual: Any) {
    assertEquals(false, actual)
}
