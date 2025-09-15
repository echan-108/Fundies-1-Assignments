// QUESTION 1
class Person(var name: String, var age: Int)

fun getPeopleInTheClub(people: List<Person>): List<String> {
    return people.map { it.name }
}
