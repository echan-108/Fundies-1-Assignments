import kotlin.random.Random

// 1. Create a Boolean val property "isAlive" that is true unless numHearts is 0
//    (or, equivalently, status == Status.Dead). It should have a custom getter
//    like the existing status property.
//
//    Create a new method "testIsAlive" that tests isAlive with every possible
//    status value (i.e., Healthy, Injured, and Dead). Uncomment the call to
//    testIsAlive() in runTests().
//
//    Run the tests and ensure they pass before proceeding.
//

/** Tests the isAlive property of a Mob. */
fun testIsAlive() {

    val testMob = Mob("Pig", 10, Mob.Behavior.Passive, 0, 0)
    // testing full health
    assertTrue(testMob.isAlive)
    testMob.takeDamage(2)
    // testing injury
    assertTrue(testMob.isAlive)
    // testing death
    testMob.takeDamage(100)
    assertFalse(testMob.isAlive)

    println("\nAll Tests Pass for isAlive")
}

//
// 2. Minecraft mobs can be Passive, Hostile, Neutral, or Boss.
//    * Passive mobs do not attack the player under any circumstances.
//    * Neutral mobs attack the player only if they were attacked first.
//    * Hostile and Boss mobs always attack the player.
//    (My instructions take priority over actual Minecraft rules, in case of
//    conflict.)
//
//    Create a Boolean val property "isAggressive" with a custom getter.
//    * It should always be true for a living Mob whose behavior is Hostile or
//      Boss.
//    * It should never be true for a mob whose behavior is Passive.
//    * If a mob is Neutral, it should be false until the Mob has been attacked,
//      after which it becomes true until it dies.
//    * Dead mobs always have an isAggressive value of false.
//    You must use "when" in your custom getter.
//
//    Add a new method "testIsAggressive" that covers every relevant
//    combination. For example, you would need two tests for a Boss Mob: one
//    when it is alive (when isAggressive should be true) and one when it is
//    dead (when isAggressive should be false). You will need more cases for
//    Neutral Mobs, which go from false to true (when attacked) to false (when
//    dead).
//
//    Call testIsAggressive() from runTests(). Ensure that all tests pass
//    before proceeding.

/** tests the isAggressive property of a Mob. */
fun testIsAggressive() {
    var bossMob = Mob("Wither", 100, Mob.Behavior.Boss, 10, 20)
    var hostileMob = Mob("Zombie", 20, Mob.Behavior.Hostile, 2, 5)
    var neutralMob = Mob("Wolf", 10, Mob.Behavior.Neutral, 2, 5)
    var passiveMob = Mob("Sheep", 10, Mob.Behavior.Passive, 0, 0)

    assertTrue(bossMob.isAggressive)
    bossMob.takeDamage(1000)
    assertFalse(bossMob.isAggressive)

    assertTrue(hostileMob.isAggressive)
    hostileMob.takeDamage(200)
    assertFalse(hostileMob.isAggressive)

    assertFalse(neutralMob.isAggressive)
    neutralMob.takeDamage(5)
    assertTrue(neutralMob.isAggressive)
    neutralMob.takeDamage(1000)
    assertFalse(neutralMob.isAggressive)

    assertFalse(passiveMob.isAggressive)
    passiveMob.takeDamage(2)
    assertFalse(passiveMob.isAggressive)
    passiveMob.takeDamage(1000)
    assertFalse(passiveMob.isAggressive)

    println("All tests pass for isAggressive")
}

// 3. Add properties minDamage and maxDamage (both Int) to the Mob constructor.
//    They specify how much damage the Mob can do to the player. You will have
//    to change all calls to the Mob constructor to include these values.
// DONE

//
// 4. Complete the method attack(), as described below in the code. Note that
//    if there is a call to zombie.attack(skeleton), the zombie would be
//    attacking the skeleton.
//
//    Add a new test function, "testAttack". Because attack() has some randomness,
//    your tests will have to use range of values. For example, if a skeleton
//    that can do 1-3 hearts of damage attacks a zombie that starts with 20 hearts,
//    you could check if the zombie's number of hearts is 17-19 after a single attack.
//    Call testAttack() from runTests(), and make sure all tests pass before proceeding.
//

/** Tests the attack Method of Mob */
fun testAttack() {
    val skeleton = Mob("Skeleton", 20, Mob.Behavior.Hostile, 1, 3)
    val creeper = Mob("Creeper", 20, Mob.Behavior.Hostile, 6, 12)

    skeleton.attack(creeper)
    assertTrue(creeper.numHearts in 17..19)

    creeper.attack(skeleton)
    assertTrue(skeleton.numHearts in 8..14)

    println("All tests pass for attack()")
}

//
// 5. Complete the method battle(), as described below in the code.
// DONE

//
// 6. Write a function named "doBattle()" outside of the class. It should have
//    no parameters and should create two Mobs and have them fight to the death.
//
//    Modify main() to make it call doBattle(). You may comment out the call
//    to runTests().
//
//    Include a transcript in Summary.md.

/** Simulates a battle between two Mobs, Creeper and Skeleton. */
fun doBattle() {
    val mob1 = Mob("Skeleton", 20, Mob.Behavior.Hostile, 4, 6)
    val mob2 = Mob("Creeper", 20, Mob.Behavior.Hostile, 10, 15)

    mob1.battle(mob2)
}

//
// 7. If you have extra time and energy, add more functionality. One idea is to
//    create multiple Mobs and have them battle in pairs until there is only one
//    survivor ("Mob Madness"). You could decide whether their health should be
//    topped off between battles.
//
//    Don't change any of the methods you implemented for the required parts of
//    the assignment.
//
//    There is no extra credit. This is just for fun and possible prizes.

// function for optional challenge

/**
 * Takes four mobs [mob1], [mob2], [mob3], and [mob4] and has them battle in pairs until there is
 * only one survivor which is returned
 */
fun bracketBattle(mob1: Mob, mob2: Mob, mob3: Mob, mob4: Mob): Mob {
    val winner1 = mob1.battleWithReturn(mob2)
    val winner2 = mob3.battleWithReturn(mob4)
    return winner1.battleWithReturn(winner2)
}

/**
 * A Minecraft mob.
 * @property type the type (species)
 * @property maxHearts the maximum health level
 * @property behavior the mob's behavior (Passive, Hostile, Neutral, or Boss)
 */
class Mob(
        val type: String,
        val maxHearts: Int,
        val behavior: Behavior,
        val minDamage: Int,
        val maxDamage: Int
) {
    enum class Behavior {
        Passive,
        Hostile,
        Neutral,
        Boss
    }

    enum class Status {
        Healthy,
        Injured,
        Dead
    }

    /** Returns true if a mob is able to attack, returns false otherwise */
    val isAggressive
        get() =
                if (status == Status.Dead) {
                    false
                } else if (behavior == Behavior.Boss || behavior == Behavior.Hostile) {
                    true
                } else if (behavior == Behavior.Neutral && status == Status.Injured) {
                    true
                } else {
                    false
                }

    var numHearts = maxHearts
        private set

    /** Returns true if numHearts is greater than zero, returns false otherwise */
    val isAlive
        get() =
                when (status) {
                    Status.Dead -> false
                    else -> true
                }

    /** Returns the status of the mob depending on the number of hearts */
    val status
        get() =
                when (numHearts) {
                    maxHearts -> Status.Healthy
                    0 -> Status.Dead
                    else -> Status.Injured
                }

    override fun toString() = type

    /**
     * Takes up to [damage] hearts of damage, to a maximum of [numHearts], printing a message with
     * the amount of damage taken and the resulting [status].
     */
    fun takeDamage(damage: Int) {
        val actualDamage = if (damage > numHearts) numHearts else damage
        numHearts -= actualDamage
        val text = if (actualDamage == 1) "heart" else "hearts"
        println("The $type took $actualDamage $text of damage.")
        println("It is now $status.")
    }

    /**
     * Attacks [victim], doing between [minDamage] and[maxDamage] (inclusive) hearts of damage.
     *
     * @throws IllegalArgumentException unless [isAggressive]
     */
    fun attack(victim: Mob) {
        // First, use require() to ensure that this Mob's isAggressive
        // property is true.
        //
        // If so, select a random number between minDamage and maxDamage
        // (including those values) and make the victim take that much
        // damage. For example, if minDamage was 3 and maxDamage was 5,
        // this would do 3, 4, or 5 hearts of damage.
        require(isAggressive) { "Mob is not aggressive" }

        val damage = Random.nextInt(minDamage, maxDamage + 1)
        victim.takeDamage(damage)
    }

    /**
     * Simulates a battle to the death with [opponent]. The property [isAggressive] must be true for
     * at least one of them, or neither would damage the other and the fight would never end.
     *
     * @throws IllegalArgumentException if [isAggressive] is false for
     * ```
     *     both this Mob and its opponent
     * ```
     */
    fun battle(opponent: Mob) {
        // First, use require() to ensure that isAggressive is true
        // for at least one of the two Mobs.
        //
        // This Mob and its opponent take turns. A Mob does nothing
        // during its turn unless it isAggressive, in which case
        // call attack() with the other Mob as its victim.
        //
        // When one of the two Mobs is dead, the winner is announced
        // (printed), and the method ends (returns). Do not use the
        // keywords "break" or "continue" in this method. Do use "while".
        require(this.isAggressive || opponent.isAggressive)

        var opponentTurn = false
        while (this.isAlive && opponent.isAlive) {
            if (!opponentTurn && this.isAggressive) {
                attack(opponent)
                opponentTurn = true
            } else if (opponentTurn && opponent.isAggressive) {
                opponent.attack(this)
                opponentTurn = false
            }
        }

        // declares winner
        if (!opponentTurn) {
            println("${opponent.type} has won!")
        } else {
            println("${this.type} has won!")
        }

        // blank line for formatting
        println()
    }

    // helper method for optional challenge
    /**
     * Simulates a battle to the death with [opponent]. The property [isAggressive] must be true for
     * atleast one of the two mobs Returns the winner
     */
    fun battleWithReturn(opponent: Mob): Mob {
        require(this.isAggressive || opponent.isAggressive)

        var opponentTurn = false
        while (this.isAlive && opponent.isAlive) {
            if (!opponentTurn && this.isAggressive) {
                attack(opponent)
                opponentTurn = true
            } else if (opponentTurn && opponent.isAggressive) {
                opponent.attack(this)
                opponentTurn = false
            }
        }
        // blank line for formatting
        println()

        // returns winner
        if (!opponentTurn) {
            return opponent
        } else {
            return this
        }
    }
}

fun main() {
    // runTests()
    println("BATTLE BETWEEN TWO MOBS: ")
    doBattle()

    // mob madness (Question 7)
    println("MOB MADNESS: ")
    // West Bracket
    val zombie = Mob("Zombie", 20, Mob.Behavior.Hostile, 2, 5)
    val skeleton = Mob("Skeleton", 20, Mob.Behavior.Hostile, 4, 6)
    val enderDragon = Mob("Ender Dragon", 200, Mob.Behavior.Boss, 10, 20)
    val wither = Mob("Wither", 300, Mob.Behavior.Boss, 10, 20)

    // East Bracket
    val warden = Mob("Warden", 500, Mob.Behavior.Boss, 6, 10)
    val elderGuardian = Mob("Elder Guardian", 80, Mob.Behavior.Boss, 4, 8)
    val creeper = Mob("Creeper", 20, Mob.Behavior.Hostile, 10, 15)
    val spider = Mob("Spider", 16, Mob.Behavior.Hostile, 2, 4)

    val westWinner = bracketBattle(zombie, enderDragon, skeleton, wither)
    val eastWinner = bracketBattle(warden, creeper, elderGuardian, spider)

    val finalWinner = westWinner.battleWithReturn(eastWinner)

    println("The winner of Mob Madness is: ${finalWinner.type}")
}

fun runTests() {
    testIsAlive()
    testIsAggressive()
    testAttack()
}
