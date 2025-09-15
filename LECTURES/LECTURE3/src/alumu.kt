val alums: MutableMap<String, MutableList<String>> = mutableMapOf(
    "NU" to mutableListOf("Toby Fox")
)

fun main() {
    // Add another value to NU entry.
    // TODO("Add Biz Stone for NU")
    alums["NU"]?.add("Bix Stone")
q
    // Create a UCB entry.
    //TODO("Add Ken Thompson for UCB")
    alums["UCB"] = mutableListOf("Ken Thompson")

    // Prompt user for a school-grad pair.
    print("Enter a school: ")
    val school = readln()
    print("Enter a graduate: ")
    val grad = readln()


    // Add it to our map.
    if (school in alums) {
        // If we know the school, add it to the existing list.
        // TODO("Add grad to existing school entry")
        alums[school]?.add(grad)
    } else {
        // Otherwise create a new list containing the grad.
        // TODO("Add grad to new school entry")
        alums.put(school, mutableListOf(grad))
    }

    // Output information for all schools.
    for (univ in alums.keys) {
        print("\n$univ graduates: ${alums[univ]}")
    }
}