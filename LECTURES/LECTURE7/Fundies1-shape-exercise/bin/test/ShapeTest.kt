import kotlin.math.PI
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

// Tests written by claude.ai.
class ShapeTest {
    @Test
    fun testCircleArea() {
        val circle = Circle(5.0)
        assertEquals(PI * 25, circle.calculateArea(), 0.001)
    }

    @Test
    fun testCirclePerimeter() {
        val circle = Circle(5.0)
        assertEquals(2 * PI * 5, circle.calculatePerimeter(), 0.001)
    }

    @Test
    fun testCircleToString() {
        val circle = Circle(5.0)
        assertEquals("Circle with radius 5.0", circle.toString())
    }

    @Test
    fun testRectangleArea() {
        val rectangle = Rectangle(4.0, 5.0)
        assertEquals(20.0, rectangle.calculateArea(), 0.001)
    }

    @Test
    fun testRectanglePerimeter() {
        val rectangle = Rectangle(4.0, 5.0)
        assertEquals(18.0, rectangle.calculatePerimeter(), 0.001)
    }

    @Test
    fun testRectangleToString() {
        val rectangle = Rectangle(4.0, 5.0)
        assertEquals("Rectangle with width 4.0 and height 5.0", rectangle.toString())
    }

    @Test
    fun testSquareArea() {
        val square = Square(4.0)
        assertEquals(16.0, square.calculateArea(), 0.001)
    }

    @Test
    fun testSquarePerimeter() {
        val square = Square(4.0)
        assertEquals(16.0, square.calculatePerimeter(), 0.001)
    }

    @Test
    fun testSquareToString() {
        val square = Square(4.0)
        assertEquals("Square with side 4.0", square.toString())
    }

    @Test
    fun testEquilateralTriangleArea() {
        val triangle = EquilateralTriangle(4.0)
        val expectedArea = (sqrt(3.0) / 4) * 16
        assertEquals(expectedArea, triangle.calculateArea(), 0.001)
    }

    @Test
    fun testEquilateralTrianglePerimeter() {
        val triangle = EquilateralTriangle(4.0)
        assertEquals(12.0, triangle.calculatePerimeter(), 0.001)
    }

    @Test
    fun testEquilateralTriangleToString() {
        val triangle = EquilateralTriangle(4.0)
        assertEquals("Equilateral Triangle with side 4.0", triangle.toString())
    }

    @Test
    fun testRightTriangleArea() {
        val triangle = RightTriangle(3.0, 4.0)
        assertEquals(6.0, triangle.calculateArea(), 0.001)
    }

    @Test
    fun testRightTrianglePerimeter() {
        val triangle = RightTriangle(3.0, 4.0)
        assertEquals(12.0, triangle.calculatePerimeter(), 0.001)
    }

    @Test
    fun testRightTriangleToString() {
        val triangle = RightTriangle(3.0, 4.0)
        assertEquals("Right Triangle with base 3.0 and height 4.0", triangle.toString())
    }

    //    @Test
    //    fun testPolymorphism() {
    //        val shapes: List<Shape> = listOf(
    //            Circle(5.0),
    //            Rectangle(4.0, 5.0),
    //            Square(4.0),
    //            EquilateralTriangle(4.0),
    //            RightTriangle(3.0, 4.0)
    //        )
    //
    //        val areas = shapes.map { it.calculateArea() }
    //        val expectedAreas = listOf(
    //            PI * 25,
    //            20.0,
    //            16.0,
    //            (sqrt(3.0) / 4) * 16,
    //            6.0
    //        )
    //
    //        for (i in areas.indices) {
    //            assertEquals(expectedAreas[i], areas[i], 0.001)
    //        }
    //    }
}
