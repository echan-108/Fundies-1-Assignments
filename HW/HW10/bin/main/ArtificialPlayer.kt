import java.lang.Thread.sleep

class ArtificialPlayer(ui: UserInterface) : Player(ui) {
    // Source of real Wordle word list: https://github.com/tabatkins/wordle-list
    private val wordList = readWordsFromFile("input-words.txt")
    private val constraints: MutableList<(String) -> Boolean> = mutableListOf()
    private val setLetters: MutableSet<Char> = mutableSetOf()

    override fun startNewGame() {
        constraints.clear()
    }

    override fun generateGuess(): String {
        // Pause for one second so the play doesn't move too fast.
        sleep(1000L)
        val possibleWords = wordList.filter { word -> constraints.all { constraint -> constraint(word) } }.shuffled()
        // Return the first word that satisfies all the constraints.
        // You may want to write a helper method.
        // scrambles the possible words list so that in certain edge cases, a different work is picked
        return if (possibleWords[0].isNotEmpty()) possibleWords[0] else "     "
    }

    override fun incorporateFeedback(guess: String, matchString: String) {
        for (i in guess.indices) {
            when {
                matchString[i] == '*' -> {
                    setLetters.add(guess[i])
                    constraints.add { s: String -> s[i] == guess[i] }
                }

                matchString[i] == '+' -> {
                    setLetters.add(guess[i])
                    constraints.add { s: String -> s.contains(guess[i]) && s[i] != guess[i] }
                }
            }
        }
        // separate for loop so the knowledge base is created before looking for '.'
        for (i in guess.indices) {
            when {
                matchString[i] == '.' && !setLetters.contains(guess[i]) -> {
                    constraints.add { s: String -> !s.contains(guess[i]) }
                }
            }
        }
    }
}