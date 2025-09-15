class Person(val firstName: String, val lastName: String?) {
    override fun toString(): String {
        return if (lastName == null) {
            "$firstName"
        } else {
            "$firstName $lastName"
        }
    }
}

fun main() {
    val person1 = Person("John", "Doe")
    val person2 = Person("Beyonce", null)
    println(person1)
    println(person2)
}
