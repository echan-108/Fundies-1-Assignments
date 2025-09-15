import kotlin.math.sqrt

abstract class Shape {
    /** Calculates the area of this shape. */
    abstract fun calculateArea(): Double

    /** Calculates the perimeter of this shape. */
    abstract fun calculatePerimeter(): Double
}

class Circle(val radius: Double) : Shape() {
    override fun calculateArea() = Math.PI * radius * radius
    override fun calculatePerimeter() = 2 * Math.PI * radius
    override fun toString() = "Circle with radius $radius"
}

open class Rectangle(val width: Double, val height: Double) : Shape() {
    override fun calculateArea() = width * height
    override fun calculatePerimeter() = 2 * (width + height)
    override fun toString() = "Rectangle with width $width and height $height"
}

class Square(val side: Double) : Rectangle(side, side) {
    override fun calculateArea() = side * side
    override fun calculatePerimeter() = 4 * side
    override fun toString() = "Square with side $side"
}

abstract class Triangle(val side1: Double, val side2: Double, val side3: Double) : Shape() {}

class RightTriangle(val leg1: Double, val leg2: Double) :
        Triangle(leg1, leg2, sqrt((leg1 * leg1) + (leg2 * leg2))) {
    override fun calculateArea() = 0.5 * leg1 * leg2
    override fun calculatePerimeter() = leg1 + leg2 + sqrt((leg1 * leg1) + (leg2 * leg2))
    override fun toString() = "Right Triangle with legs $leg1 and $leg2"
}

class EquilateralTriangle(val side: Double) : Triangle(side, side, side) {
    override fun calculateArea() = (sqrt(3.0) / 4) * side * side
    override fun calculatePerimeter() = 3 * side
    override fun toString() = "Equilateral Triangle with side $side"
}
