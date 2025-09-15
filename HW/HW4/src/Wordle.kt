import kotlin.random.Random

// You will be implementing a version of the game Wordle. If you are not
// familiar with it, learn how to play.
// https://www.nytimes.com/games/wordle/index.html
//
// Our version will not use colors.
// * It will use "*" (instead of green) for correct letters in the guess that
//   are in the correct position.
// * It will use "+" (instead of yellow) for letters that are in the secret word
//   but not in the correct position.
// * It will use "." for any guessed letters not in the secret word.
// See the provided method runTests() for examples.
//
// To make the logic easier, our game will have these differences:
// * Our secret words (answers) will have no repeating letters.
// * You can assume that there are no repeating letters in the guess.
// * You will be able to specify how long the secret word should be, which
//   should make debugging easier. For example, you can start with puzzles
//   of length 1 or 2.
// * Guesses do not have to be legal words. For example, you can guess "AEIOU".
// * There are an unlimited number of guesses.
//
// 0. Look over the provided functions in SupportCode.kt and the section
//    on useful functions and methods on Canvas.
//
// 1. Add properties (that are not parameters) to class WordleGame:
//    * guesses (MutableList<String>) [all guesses made]
//    * numGuesses (Int) [number of guesses made, custom getter]
//    * wordFound (Boolean) [whether the secret word has been guessed, custom
//      getter]
//    You must use a custom getter for numGuesses and wordFound.
//
// 2. You are provided with runTests5(), which runs tests on a 5-letter secret
//    word. Make sure you understand it. Remove the comment markers once you
//    have added the properties they reference.
//
//    Create similar methods for secret words of length 1, 2, 3, and 4. You must
//    have at least 5 assertions in each of your 4 test methods. You may use AI
//    to write or check your tests, and you may ask classmates or TAs to look over
//    your tests to make sure they are correct. Remember to list people/AI you worked
//    with in your write-up and that you are ultimately responsible for correctness.
//
//    Add calls from runTests() to your new functions, ordering them by
//    increasing word length.
//
//    Try running your tests. They should fail. That's good.
//    https://learning.oreilly.com/library/view/modern-c-programming/9781941222423/f_0054.html
//
// 3. Implement makeGuess(), as described in the KDoc, for words of length 1.
//    Note that we are no longer providing detailed instructions in the
//    comments. Do not proceed to the next step until runTests1() passes.
//
// 4. Repeat the process for words of length 2, 3, 4, and 5. You should write
//    a general solution that will work for strings of any length, but you may
//    find it easier to add one letter at a time until you find the general
//    pattern.
//
// 5. Implement playGame() as described in the KDoc and internal comments.
//    Modify main() so it calls playGame() after runTests().
//
// 6. Modify playGame() so it prompts the user after each game asking if
//    they would like to play again. If they indicate that they do, start
//    a fresh game with a different word. For full credit, do not reload the
//    word list, which is an expensive operation.
//
// 7. Put a transcript of your program in Summary.md. You should use the longest
//    word length you successfully implemented and show that the user could play
//    more than one game without restarting the program.
//
//    If you want an extra challenge, figure out how to correctly handle words
//    with repeated letters or to enforce that guesses are legal words.

/**
 * A single Wordle game.
 *
 * @property secretWord the word for the user to guess
 */
class WordleGame(
    val secretWord: String,
) {
    val guesses = mutableListOf<String>()
    val numGuesses
        get() = guesses.size
    val wordFound
        get() = if (guesses.size > 0) guesses.contains(secretWord) else false

    /**
     * Guesses that the secret word is [guess]. This returns a feedback String. For every position
     * where [guess] matches [secretWord], a star (*) appears. For positions where the letter in
     * [guess] corresponds to a different position in [secretWord], a plus sign (+) appears. If
     * there is no match, a dot (.) appears.
     *
     * @throws IllegalArgumentException if the length of [guess] is not the same as the length of
     * [secretWord]
     */
    fun makeGuess(guess: String): String {
        // If guess has not been played this game, add it to guesses.
        // Otherwise, it doesn't count as a new guess, but you should
        // still give feedback.
        if (!guesses.contains(guess)) {
            guesses.add(guess)
        }

        var output = StringBuilder()
        for (i in 0..secretWord.length - 1) {
            if (guess[i] == secretWord[i]) {
                output.append("*")
            } else if (secretWord.contains(guess[i])) {
                output.append("+")
            } else {
                output.append(".")
            }
        }
        return output.toString()
    }
}

/**
 * Plays Wordle.
 *
 * @property wordLength the length of the secret word
 */
fun playGame(wordLength: Int) {
    // Call readWordsFromFile() to get the random list of words.
    // Change so that the list is not reloaded if the game is replayed, probably implemnt inner
    // while loop and ditch the recursive approach

    val wordList = readWordsFromFile(wordLength)

    while (true) {
        val randomWord = wordList[Random.nextInt(0, wordList.size - 1)]
        val game = WordleGame(randomWord)

        println("Please input a guess that is $wordLength letters long: ")

        while (!game.wordFound) {
            var guess = readln()
            if (guess.length != wordLength) {
                println("Please enter a guess of the correct word length")
            } else {
                println(game.makeGuess(guess.uppercase()))
            }
            println("WRONG! Please enter another guess: ")
        }

        println("Good Job! You won! It took you ${game.numGuesses} to guess the word,")
        println("These guesses were")
        for (guess in game.guesses) {
            println("${guess},")
        }

        println("Would you like to play again? Y for yes and N for no")
        var response = readln()
        if (response.uppercase() == "Y") {
            println("")
        } else if (response.uppercase() == "N") {
            println("Thanks For Playing! Goodbye!")
            break
        } else {
            println("Please enter a valid response")
        }
    }
}

fun main() {
    runTests()
    playGame(5)
}

fun runTests() {
    runTests1()
    runTests2()
    runTests3()
    runTests4()
    runTests5()
    println("All tests pass.")
}

fun runTests5() {

    val game = WordleGame("ABCDE")
    assertEquals(0, game.numGuesses)
    assertFalse(game.wordFound)
    assertEquals(".....", game.makeGuess("FGHIJ"))
    assertEquals("++*++", game.makeGuess("EDCBA"))
    assertEquals(".*+++", game.makeGuess("ZBDEC"))
    assertFalse(game.wordFound)
    assertEquals(3, game.numGuesses)
    assertEquals(".....", game.makeGuess("FGHIJ"))
    assertEquals(3, game.numGuesses)
    assertEquals("*****", game.makeGuess("ABCDE"))
    assertEquals(4, game.numGuesses)
    assertTrue(game.wordFound)
    assertEquals(
        mutableListOf("FGHIJ", "EDCBA", "ZBDEC", "ABCDE"),
        game.guesses,
    )
}

// Assisted by Github Copilot
fun runTests4() {
    val game = WordleGame("ABCD")
    assertEquals(0, game.numGuesses)
    assertFalse(game.wordFound)
    assertEquals("....", game.makeGuess("EFGH"))
    assertEquals("++++", game.makeGuess("DCBA"))
    assertEquals(".***", game.makeGuess("YBCD"))
    assertFalse(game.wordFound)
    assertEquals(3, game.numGuesses)
    assertEquals("....", game.makeGuess("EFGH"))
    assertEquals(3, game.numGuesses)
    assertEquals("****", game.makeGuess("ABCD"))
    assertEquals(4, game.numGuesses)
    assertTrue(game.wordFound)
    assertEquals(
        mutableListOf("EFGH", "DCBA", "YBCD", "ABCD"),
        game.guesses,
    )
}

// Assisted by Github Copilot
fun runTests3() {
    val game = WordleGame("ABC")
    assertEquals(0, game.numGuesses)
    assertFalse(game.wordFound)
    assertEquals("...", game.makeGuess("DEF"))
    assertEquals("+*+", game.makeGuess("CBA"))
    assertEquals(".**", game.makeGuess("YBC"))
    assertFalse(game.wordFound)
    assertEquals(3, game.numGuesses)
    assertEquals("...", game.makeGuess("DEF"))
    assertEquals(3, game.numGuesses)
    assertEquals("***", game.makeGuess("ABC"))
    assertEquals(4, game.numGuesses)
    assertTrue(game.wordFound)
    assertEquals(
        mutableListOf("DEF", "CBA", "YBC", "ABC"),
        game.guesses,
    )
}

// Assisted by Github Copilot
fun runTests2() {
    val game = WordleGame("AB")
    assertEquals(0, game.numGuesses)
    assertFalse(game.wordFound)
    assertEquals("..", game.makeGuess("CD"))
    assertEquals("++", game.makeGuess("BA"))
    assertEquals(".*", game.makeGuess("CB"))
    assertFalse(game.wordFound)
    assertEquals(3, game.numGuesses)
    assertEquals("..", game.makeGuess("CD"))
    assertEquals(3, game.numGuesses)
    assertEquals("**", game.makeGuess("AB"))
    assertEquals(4, game.numGuesses)
    assertTrue(game.wordFound)
    assertEquals(
        mutableListOf("CD", "BA", "CB", "AB"),
        game.guesses,
    )
}

// Assisted by Github Copilot
fun runTests1() {
    val game = WordleGame("A")
    assertEquals(0, game.numGuesses)
    assertFalse(game.wordFound)
    assertEquals(".", game.makeGuess("B"))
    assertEquals(1, game.numGuesses)
    assertEquals(".", game.makeGuess("C"))
    assertEquals(2, game.numGuesses)
    assertEquals("*", game.makeGuess("A"))
    assertTrue(game.wordFound)
    assertEquals(
        mutableListOf("B", "C", "A"),
        game.guesses,
    )
}
