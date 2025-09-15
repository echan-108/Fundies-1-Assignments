data class StaffRecord(val id: Int, val name: String?, val email: String)

val records =
        listOf(
                StaffRecord(id = 50, name = "Alvaro", email = "alvaro@neu.edu"),
                StaffRecord(id = 30, name = null, email = "beth@neu.edu"),
                StaffRecord(id = 20, name = "Ben", email = "ben@neu.edu")
        )

fun getEmailAddress(id: Int): String? {
    // Look up the id in records.
    // If record is not present, return null
    // If name is present, use this format: "Ben <ben@neu.edu>"
    // If name is null, use this format: "ben@neu.edu <ben@neu.edu>"
    for (record in records) {
        if (record.id == id) {
            return "${record.name ?: record.email} <${record.email}>"
        }
    }
    return null
}

fun main() {
    assertEquals("Alvaro <alvaro@neu.edu>", getEmailAddress(50))
    assertEquals("beth@neu.edu <beth@neu.edu>", getEmailAddress(30))
    assertNull(getEmailAddress(0))
    println("All tests pass.")
}

/**
 * Verifies that [actual] is equal to [expected].
 *
 * @throws AssertionError if they are not equal
 */
fun assertEquals(
        expected: Any?,
        actual: Any?,
) {
    if (expected != actual) {
        throw AssertionError("Expected $expected, got $actual")
    }
}

/**
 * Verifies that [actual] is `null`.
 *
 * @throws AssertionError if [actual] is not `null`
 */
fun assertNull(actual: Any?) {
    if (actual != null) {
        throw AssertionError("Expected null, got $actual")
    }
}
