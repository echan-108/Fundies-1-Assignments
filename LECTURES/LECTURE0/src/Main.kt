//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println(max(1, 2, 3))
    println(max(3, 5))
    println(max(13, 0))
    println(max(1, 2, 3))
    println(max(3, -1, 0))
    println(max(0, 7, 4))
}

fun max(x: Int, y: Int): Int = if (x > y) x else y

// Possible Optimizations
// Condense If Statements Using Boolean Operators
// Remove Braces and change formatting things
//

/**
fun max(x: Int, y: Int, z: Int): Int =
if (x > y) {
if (x > z) {
x
} else {
z
}
} else {
if (y > z) {
y
} else {
z
}
}
 **/

/**
fun max(x: Int, y: Int, z: Int) = if (x >= y && x >= z) x else if (y >= z && y >= x) y else z
 **/

/**
 * fun max(x: int, y: Int, z: Int) = max(x, max(z, y))
 */

fun max(x: Int, y: Int, z: Int): Int {
    var result = x;
    if (y > result) {
        result = y
    }
    if (z > result) {
        result = z
    }
    return result
}

data class Point(val x: Double, val y: Double)

fun getFurthestPoint(p1: Point, p2: Point): Point {
    val d1 = getDistanceFromOrgin(p1)
    val d2 = getDistanceFromOrgin(p2)
    return if (d1 > d2) {
        p1
    } else {
        p2
    }
}

fun getDistanceFromOrgin(p: Point): Double {
    return sqrt((p.x * p.x) + (p.y * p.y))
}

enum class house(val place: String) {
    Gold("First"),
    Silver("Second"),
    Bronze("Thrid")


}