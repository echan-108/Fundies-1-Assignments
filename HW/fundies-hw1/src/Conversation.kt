/**
 * Prompts a user to enter their name.
 *
 * @return the text entered by the user
 */
fun getUserName(): String {
    println("What is your name?")
    val newName = readln()
    return newName
}

/**
 * Greets the user by [name].
 */
fun greetUser(name: String) {
    println("Hello, $name!")
    println()
}

/**
 * Asks the user for their major
 */
fun askMajor(): String {
    println("What is your major?")
    val major = readln();
    return major
}

/**
 *  Says that the users [major] is cool
 */
fun celebrateMajor(major: String) {
    println("${major} is a very cool major!")
}

/**
 * Asks the user about their day
 */
fun askAboutDay(): String {
    println("Was your day good or bad?")
    val mood = readln()
    return mood
}

/**
 * Responds to the user about their day depending on the [mood]
 */
fun respondToDay(mood: String) {
    if (mood == "bad") {
        println("I'm sorry your day was not good")
    } else if (mood == "good") {
        println("I'm glad your day went well!")
    } else {
        println("hmm, I don't know how to feel about that")
    }
}

/**
 * Carries on a brief conversation with a user.
 */
fun converse() {
    val name = getUserName()
    greetUser(name)
    val major = askMajor()
    celebrateMajor(major)
    val mood = askAboutDay()
    respondToDay(mood)
}
