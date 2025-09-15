// Congratulations! You've been invited to do the following
// online assessment for a co-op with tech giant Oodle.
//
// 1. Add the 3+ constants from the pre-exercise to the Gender enum.
enum class Gender {
    Male,
    Female,
    Nonbinary
}

// 2. Define at least 2 age-based constants where indicated below to
//    make your later ad-serving code more readable and maintainable.
//    Choose general names, such as MIN_ADULT_AGE rather than ones that
//    refer to ads, such as MIN_AGE_FOR_DATING_AD. You can also use
//    numeric literals. (Look up terms if you don't understand them.
//    They may appear on tests!)\
// INTELLIJ suggested const keyword
const val MIN_CHILD_AGE = 13
const val MIN_ADULT_AGE = 18
const val MID_ADULT_AGE = 32
const val MAX_ADULT_AGE = 45
const val MIN_SENIOR_AGE = 65
const val MAX_AGE = 80


// 3. Add properties minAge and maxAge to Ad, and set values for each
//    ad. For example, minAge for the dating ad might be MIN_ADULT_AGE.
// DONE


// 4. Implement the provided fetchAd() function. You must use both "if"
//    and "when". Paste in the tests from the pre-exercise. Uncomment
//    the call to runTests() in main(). Run the tests, and see if they
//    all pass. In your write-up, you will need to describe the testing
//    and debugging process, so take notes.
//


// 5. Create a new data class named "Person". A person should have an
//    age (Int), gender (Gender), and income (Int). Use your judgment
//    as to which properties should be changeable.
// 6. Write a new fetchAd() method (without removing the original one)
//    that takes a single parameter of type Person and returns an Ad.
//    Instead of duplicating the code in your original fetchAd() method,
//    have your new method call your old method, passing the appropriate
//    properties as arguments.
// 7. Create a new function named "testFetchAdPerson" that tests your
//    new fetchAd() method. Modify runTests() to call this new function.

/**
 * Ads that may be shown to users.
 *
 * @property text the text of the ad
 * @property revenue the number of cents earned per click
 */
enum class Ad(
    val text: String,
    val revenue: Int,
    val minAge: Int,
    val maxAge: Int,
) {
    Diet("Lose weight now!", 5, MIN_CHILD_AGE, MIN_SENIOR_AGE),
    Dating("Meet other singles!", 4, MIN_ADULT_AGE, MIN_SENIOR_AGE),
    Lego("Fun to step on!", 1, MIN_CHILD_AGE, MID_ADULT_AGE),
    Pet("You need a friend!", 1, MIN_CHILD_AGE, MAX_AGE),
    PetToy("Amuse your pet!", 2, MIN_CHILD_AGE, MAX_AGE),
    Pokemon("Gotta catch 'em all!", 2, MIN_CHILD_AGE, MID_ADULT_AGE),
    Retirement("Join AARP", 2, MID_ADULT_AGE, MAX_ADULT_AGE),
    Work("Apply for a job at Oodle!", 2, MIN_ADULT_AGE, MAX_ADULT_AGE),
}

/**
 * Fetches an ad based on the user's [gender], [age], and
 * [income], unless the age is under [MIN_AGE_FOR_PERSONALIZATION],
 * in which case no personalization is permitted.
 */
fun fetchAd(gender: Gender, age: Int, income: Int): Ad {
    // 4a. Implement the provided fetchAd() function. You must use
    //     case tree based on spreadsheet made in pre-task
    val defaultAd = Ad.Pet
    if (age in 13..80 && income < 1000000) {
        when (income) {
            in 0..19999 -> {
                return if (gender == Gender.Male && age in MIN_CHILD_AGE..65) {
                    Ad.Diet
                } else if (gender == Gender.Female && age in MIN_CHILD_AGE..32) {
                    Ad.Pokemon
                } else if (gender == Gender.Nonbinary && age in MIN_CHILD_AGE..32) {
                    Ad.Lego
                } else {
                    defaultAd
                }

            }

            in 20000..39999 -> {
                return if (gender == Gender.Male && age in 18..65) {
                    Ad.Dating
                } else if (gender == Gender.Female && age in MIN_CHILD_AGE..65) {
                    Ad.Retirement
                } else if (gender == Gender.Nonbinary) {
                    Ad.Pet
                } else {
                    defaultAd
                }
            }

            in 40000..59999 -> {
                return if (gender == Gender.Male && age in MIN_CHILD_AGE..32) {
                    Ad.Lego
                } else if (gender == Gender.Female && age in 18..45) {
                    Ad.Work
                } else if (gender == Gender.Nonbinary) {
                    Ad.PetToy
                } else {
                    defaultAd
                }
            }

            in 60000..99999 -> {
                return if (gender == Gender.Male) {
                    Ad.Pet
                } else if (gender == Gender.Female && age in MIN_CHILD_AGE..65) {
                    Ad.Diet
                } else if (gender == Gender.Nonbinary && age in 13..32) {
                    Ad.Pokemon
                } else {
                    defaultAd
                }
            }

            in 100000..999999 -> {
                return if (gender == Gender.Male) {
                    Ad.PetToy
                } else if (gender == Gender.Female && age in 18..65) {
                    Ad.Dating
                } else if (gender == Gender.Nonbinary && age in 32..45) {
                    Ad.Retirement
                } else {
                    defaultAd
                }
            }
        }
    }
    return defaultAd
}

// 4b. Paste in the tests from the pre-exercise.
// DONE
fun testDiet1() {
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 13, 0))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 13, 19999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 13, 9999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 65, 0))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 65, 19999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 65, 9999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 39, 0))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 39, 19999))
    assertEquals(Ad.Diet, fetchAd(Gender.Male, 39, 9999))
}


fun testDating2() {
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 18, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 18, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 18, 29999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 65, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 65, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 65, 29999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 41, 20000))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 41, 39999))
    assertEquals(Ad.Dating, fetchAd(Gender.Male, 41, 29999))
}


fun testLego3() {
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 13, 40000))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 13, 59999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 13, 49999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 32, 40000))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 32, 59999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 32, 49999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 22, 40000))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 22, 59999))
    assertEquals(Ad.Lego, fetchAd(Gender.Male, 22, 49999))
}


fun testPet4() {
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 13, 60000))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 13, 99999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 13, 79999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 80, 60000))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 80, 99999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 80, 79999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 46, 60000))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 46, 99999))
    assertEquals(Ad.Pet, fetchAd(Gender.Male, 46, 79999))
}


fun testPetToy5() {
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 13, 100000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 13, 999999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 13, 549999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 80, 100000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 80, 999999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 80, 549999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 46, 100000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 46, 999999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Male, 46, 549999))
}


fun testPokemon6() {
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 13, 0))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 13, 19999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 13, 9999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 32, 0))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 32, 19999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 32, 9999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 22, 0))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 22, 19999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Female, 22, 9999))
}


fun testRetirement7() {
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 32, 20000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 32, 39999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 32, 29999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 45, 20000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 45, 39999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 45, 29999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 38, 20000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 38, 39999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Female, 38, 29999))
}


fun testWork8() {
    assertEquals(Ad.Work, fetchAd(Gender.Female, 18, 40000))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 18, 59999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 18, 49999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 45, 40000))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 45, 59999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 45, 49999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 31, 40000))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 31, 59999))
    assertEquals(Ad.Work, fetchAd(Gender.Female, 31, 49999))
}


fun testDiet9() {
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 13, 60000))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 13, 99999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 13, 79999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 65, 60000))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 65, 99999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 65, 79999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 39, 60000))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 39, 99999))
    assertEquals(Ad.Diet, fetchAd(Gender.Female, 39, 79999))
}


fun testDating10() {
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 18, 100000))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 18, 999999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 18, 549999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 65, 100000))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 65, 999999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 65, 549999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 41, 100000))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 41, 999999))
    assertEquals(Ad.Dating, fetchAd(Gender.Female, 41, 549999))
}


fun testLego11() {
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 13, 0))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 13, 19999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 13, 9999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 32, 0))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 32, 19999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 32, 9999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 22, 0))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 22, 19999))
    assertEquals(Ad.Lego, fetchAd(Gender.Nonbinary, 22, 9999))
}


fun testPet12() {
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 13, 20000))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 13, 39999))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 13, 29999))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 80, 20000))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 80, 39999))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 80, 29999))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 46, 20000))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 46, 39999))
    assertEquals(Ad.Pet, fetchAd(Gender.Nonbinary, 46, 29999))
}


fun testPetToy13() {
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 13, 40000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 13, 59999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 13, 49999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 80, 40000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 80, 59999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 80, 49999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 46, 40000))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 46, 59999))
    assertEquals(Ad.PetToy, fetchAd(Gender.Nonbinary, 46, 49999))
}


fun testPokemon14() {
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 13, 60000))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 13, 99999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 13, 79999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 32, 60000))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 32, 99999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 32, 79999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 22, 60000))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 22, 99999))
    assertEquals(Ad.Pokemon, fetchAd(Gender.Nonbinary, 22, 79999))
}


fun testRetirement15() {
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 32, 100000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 32, 999999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 32, 549999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 45, 100000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 45, 999999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 45, 549999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 38, 100000))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 38, 999999))
    assertEquals(Ad.Retirement, fetchAd(Gender.Nonbinary, 38, 549999))
}


fun testAll() {
    testDiet1()
    testDating2()
    testLego3()
    testPet4()
    testPetToy5()
    testPokemon6()
    testRetirement7()
    testWork8()
    testDiet9()
    testDating10()
    testLego11()
    testPet12()
    testPetToy13()
    testPokemon14()
    testRetirement15()
    testFetchAdPerson()
    print("All tests pass.")
}


// 4c. Uncomment the call to runTests() in main(). Run the tests, and see
//     if they all pass. In your write-up, you will need to describe the
//     testing and debugging process, so take notes.
// DONE
fun main() {
    //runTests() replaced with call to testAll because I think that is what you guys meant
    testAll()
}

// 5. Create a new data class named "Person". A person should have an
//    age (Int), gender (Gender), and income (Int). Use your judgment
//    as to which properties should be changeable.
data class Person(var gender: Gender, var age: Int, var income: Int)

// 6. Write a new fetchAd() method (without removing the original one)
//    that takes a single parameter of type Person and returns an Ad.
//    Instead of duplicating the code in your original fetchAd() method,
//    have your new method call your old method, passing the appropriate
//    properties as arguments.
fun fetchAd(person: Person): Ad = fetchAd(person.gender, person.age, person.income)

// 7. Create a new function named "testFetchAdPerson" that tests your
//    new fetchAd() method. Modify runTests() to call this new function.
fun testFetchAdPerson() {
    var person1 = Person(Gender.Male, 13, 10000) // Diet
    var person2 = Person(Gender.Male, 18, 25000) // Dating
    var person3 = Person(Gender.Female, 13, 75000) // Diet
    var person4 = Person(Gender.Female, 30, 15000) // Pokemon
    var person5 = Person(Gender.Nonbinary, 60, 55000) // PetToy
    var person6 = Person(Gender.Nonbinary, 42, 104000) // Retirement
    assertEquals(fetchAd(person1), Ad.Diet)
    assertEquals(fetchAd(person2), Ad.Dating)
    assertEquals(fetchAd(person3), Ad.Diet)
    assertEquals(fetchAd(person4), Ad.Pokemon)
    assertEquals(fetchAd(person5), Ad.PetToy)
    assertEquals(fetchAd(person6), Ad.Retirement)
}
q

// Do not modify the following code.

/**
 * Verifies that [actual] is equal to [expected].
 *
 * @throws AssertionError if they are not equal
 */
fun assertEquals(
    expected: Any,
    actual: Any,
) {
    if (expected != actual) {
        throw AssertionError("Expected $expected, got $actual")
    }
}
