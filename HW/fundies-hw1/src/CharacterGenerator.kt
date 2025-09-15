import kotlin.random.Random

/**
 * Rolls a single six-sided die.
 *
 * @return a random number in the range 1-6
 */
fun rollDie(): Int {
    // The second argument to nextInt() is the upper bound, which is exclusive,
    // so this will return a number in the range 1-6.
    return Random.nextInt(1, 7)
}

/**
 * Rolls 3 six-sided dice.
 *
 * @return the sum of 3 random numbers in the range 1-6
 */
fun rollThreeDice(): Int {
    return 3 * rollDie()
}

/**
 * Rolls a single die having numbers in the range [minValue] to [maxValue]
 * (inclusive).
 *
 * @return a random number in the range [minValue] to [maxValue] (inclusive)
 */
fun rollBiasedDie(minValue: Int, maxValue: Int): Int {
    return Random.nextInt(minValue, maxValue + 1)
}

/**
 * Rolls 3 dice having numbers in the range [minValue] to [maxValue] (inclusive).
 *
 * @return the sum of 3 random numbers in the range [minValue] to [maxValue]
 */
fun rollThreeBiasedDice(minValue: Int, maxValue: Int): Int {
    return 3 * rollBiasedDie(minValue, maxValue)
}

/**
 * Makes a character with a user-provided name and randomly-selected stats
 * using 3 ordinary six-sided dice.
 *
 * @return a sentence describing the character
 */
fun makeCharacter(): String {
    println("Enter your character's name:")
    val name = readln()
    val charisma = rollThreeDice()
    val agility = rollThreeDice()
    val speed = rollThreeDice()
    return "$name has charisma $charisma, agility $agility, and speed $speed."
}

/**
 * Makes a potentially superior character with a user-provided name and
 * randomly-selected stats using 3 positively biased dice.
 *
 * @return a sentence describing the hero
 */
fun makeHero(): String {
    // Replace this with code to get a hero's name, then generate its
    // characteristics by rolling dice with a range of 3-6 (inclusive).
    // Finally, return the name and characteristics.
    println("Enter the heroes name:")
    val name = readln()
    val charisma = rollBiasedDie(3, 6)
    val strength = rollBiasedDie(3, 6)
    val speed = rollBiasedDie(3, 6)
    return "The hero $name has charisma $charisma, strength $strength, and speed $speed"
}

/**
 * Makes a character with a user-provided name and randomly-selected stats
 * using 3 dice whose minimum and maximum values are specified by the user.
 *
 * @return a sentence describing the adventurer
 */
fun makeAdventurer(): String {
    // Replace this with code to get an adventurer's name, min die value,
    // and max die value. Generate its characteristics by rolling dice within
    // the specified range. Finally, return the names and characteristics.
    println("What is your adventure's name: ")
    val name = readln()
    println("What is the min die value: ")
    val minVal = readln()!!.toInt()
    println("What is the max die value: ")
    val maxVal = readln()!!.toInt()
    val constitution = rollBiasedDie(minVal, maxVal)
    val strength = rollBiasedDie(minVal, maxVal)
    val dexterity = rollBiasedDie(minVal, maxVal)
    return "The adventurer $name has constitution $constitution, dexterity $dexterity, and strength $strength"
}
