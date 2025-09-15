import java.awt.Color
import kotlin.random.Random

/** The desired canvas width, which must be at least 4 times [MIN_RECTANGLE_WIDTH]. */
const val REQUESTED_CANVAS_WIDTH = 400

/** The desired canvas height, which must be at least 4 times [MIN_RECTANGLE_HEIGHT]. */
const val REQUESTED_CANVAS_HEIGHT = 400

/** The minimum width of a colored rectangle. */
const val MIN_RECTANGLE_WIDTH = 80

/** The minimum height of a colored rectangle. */
const val MIN_RECTANGLE_HEIGHT = 80

// seed for random number generator
val SEED = 9999

// the random class with the seed inputted

open class MondrianPainter {
    // Use this property when generating random numbers.
    private val random = Random(SEED)

    private val canvas =
            Canvas(
                    "Mondrian Painter",
                    this,
                    REQUESTED_CANVAS_WIDTH,
                    REQUESTED_CANVAS_HEIGHT,
            )

    /**
     * Performs a side-by-side split of the region specified by [x], [y], [width], and [height], if
     * it is wide enough to split, and calls [doMondrian] on each of the two smaller regions. If the
     * original region is too narrow to split, no action is taken.
     *
     * @return true if the original region was wide enough to split, false otherwise
     */
    private fun splitLeftRight(x: Int, y: Int, width: Int, height: Int): Boolean {
        // check that this rectangle is wide enough to split
        if (width - MIN_RECTANGLE_WIDTH < MIN_RECTANGLE_WIDTH) {
            return false
        }
        val offset = random.nextInt((MIN_RECTANGLE_WIDTH), (width - MIN_RECTANGLE_WIDTH))
        // dimensions for rect0
        val rect0X = x
        val rect0Y = y
        val rect0Width = offset
        val rect0Height = height
        // dimensions for rect1
        val rect1X = x + offset
        val rect1Y = y
        val rect1Width = width - offset
        val rect1Height = height

        // left region
        doMondrian(rect0X, rect0Y, rect0Width, rect0Height)
        // right region
        doMondrian(rect1X, rect1Y, rect1Width, rect1Height)
        return true
    }

    /**
     * Performs an over-under split of the region specified by [x], [y], [width], and [height], if
     * it is tall enough to split, and calls [doMondrian] on each of the two smaller regions. If the
     * original region is too short to split, no action is taken.
     *
     * @return true if the original region was tall enough to split, false otherwise
     */
    private fun splitTopBottom(x: Int, y: Int, width: Int, height: Int): Boolean {
        if (height - MIN_RECTANGLE_HEIGHT < MIN_RECTANGLE_HEIGHT) {
            return false
        }

        val offset = random.nextInt(MIN_RECTANGLE_HEIGHT, height - MIN_RECTANGLE_HEIGHT)
        // dimensions for rect0
        val rect0X = x
        val rect0Y = y
        val rect0Width = width
        val rect0Height = offset
        // dimensions for rect1
        val rect1X = x
        val rect1Y = y + offset
        val rect1Width = width
        val rect1Height = height - offset

        // top region
        doMondrian(rect0X, rect0Y, rect0Width, rect0Height)
        // bottom region
        doMondrian(rect1X, rect1Y, rect1Width, rect1Height)
        return true
    }

    /**
     * Performs an horizontal and vertical split of the region specified by [x], [y], [width], and
     * [height], if it is both wide and tall enough to split, and calls [doMondrian] on each of the
     * four smaller regions. If the original region is too small to split, no action is taken.
     *
     * @return true if the original region could be split, false otherwise
     */
    private fun split4Way(x: Int, y: Int, width: Int, height: Int): Boolean {
        if (width - MIN_RECTANGLE_WIDTH < MIN_RECTANGLE_WIDTH) {
            return false
        } else if (height - MIN_RECTANGLE_HEIGHT < MIN_RECTANGLE_HEIGHT) {
            return false
        }

        val xOffset = random.nextInt(MIN_RECTANGLE_WIDTH, width - MIN_RECTANGLE_WIDTH)
        val yOffset = random.nextInt(MIN_RECTANGLE_HEIGHT, height - MIN_RECTANGLE_HEIGHT)

        // rectangle 0 upper left
        val rect0X = x
        val rect0Y = y
        val rect0Width = xOffset
        val rect0Height = yOffset

        // rectangle 1 lower left
        val rect1X = x
        val rect1Y = y + yOffset
        val rect1Width = xOffset
        val rect1Height = height - yOffset

        // rectangle 2 lower right
        val rect2X = x + xOffset
        val rect2Y = y + yOffset
        val rect2Width = width - xOffset
        val rect2Height = height - yOffset

        // rectangle 3 upper right
        val rect3X = x + xOffset
        val rect3Y = y
        val rect3Width = width - xOffset
        val rect3Height = yOffset

        doMondrian(rect0X, rect0Y, rect0Width, rect0Height)
        doMondrian(rect1X, rect1Y, rect1Width, rect1Height)
        doMondrian(rect2X, rect2Y, rect2Width, rect2Height)
        doMondrian(rect3X, rect3Y, rect3Width, rect3Height)

        return true
    }

    private fun chooseRandomSplit(x: Int, y: Int, width: Int, height: Int): Boolean {
        var num = random.nextInt(0, 3)

        for (i in 0 until 3) {
            var success = false

            when (num) {
                0 -> success = splitLeftRight(x, y, width, height)
                1 -> success = splitTopBottom(x, y, width, height)
                2 -> success = split4Way(x, y, width, height)
            }

            if (success) {
                return true
            }

            num = (num + 1) % 3
        }
        return false
    }

    /**
     * Divides the region with the given [x] and [y] coordinates and having width [width] and height
     * [height] into one or more colored rectangles, in the style of Piet Mondrian.
     */
    fun doMondrian(
            x: Int,
            y: Int,
            width: Int,
            height: Int,
    ) {
        // TODO: When testing the split methods, put temporary calls here.

        // 1. If the width of the region is more than half the canvas width AND
        //    the height of the region is more than half the canvas height,
        //    call split4Way(x, y, width, height).

        // 2. Otherwise, if the width of the region is more than half the
        //    canvas width (but the height is not more than half the canvas
        //    height), call splitLeftRight(x, y, width, height).

        // 3. Otherwise, if the height of the region is more than half the
        //    canvas height (but the width is not more than half the canvas
        //    width), call splitTopBottom(x, y, width, height).

        // 4. Otherwise, randomly choose a split type to attempt from these
        //    three options: LeftRight, TopBottom, or Both.
        //    * If LeftRight is chosen and the region is wide enough to split
        //      into two side-by-side regions, do so.
        //    * If TopBottom was chosen and the region is tall enough to split
        //      into two stacked (over-under) regions, do so.
        //    * If Both was chosen and the region is both wide enough and tall
        //      enough to split into four regions, do so.

        // 5. If none of the above conditions code caused a split method to be
        //    called, fill the entire region with a single color. Half the time,
        //    the color should be white. The other half of the time, choose
        //    randomly among red, yellow, and blue. The outline color should
        //    always be black. You can modify the below sample code, which
        //    always draws a yellow rectangle with a black outline.

        if (width > REQUESTED_CANVAS_WIDTH / 2 && height > REQUESTED_CANVAS_HEIGHT / 2) {
            split4Way(x, y, width, height)
        } else if (width > REQUESTED_CANVAS_WIDTH / 2) {
            splitLeftRight(x, y, width, height)
        } else if (height > REQUESTED_CANVAS_HEIGHT / 2) {
            splitTopBottom(x, y, width, height)
        } else if (width - MIN_RECTANGLE_WIDTH >= MIN_RECTANGLE_WIDTH ||
                        height - MIN_RECTANGLE_HEIGHT >= MIN_RECTANGLE_HEIGHT
        ) {
            chooseRandomSplit(x, y, width, height)
        } else {

            var currentColor = Color.WHITE

            val white = random.nextInt(0, 2)
            if (white == 0) {
                val otherColor = random.nextInt(0, 3)
                when (otherColor) {
                    0 -> currentColor = Color.YELLOW
                    1 -> currentColor = Color.RED
                    2 -> currentColor = Color.BLUE
                }
            }

            canvas.drawRectangle(
                    x,
                    y,
                    width,
                    height,
                    fillColor = currentColor,
                    outlineColor = Color.BLACK,
            )
        }
    }

    /**
     * Handles a click at the specified [x]
     * -[y] coordinates.
     */
    fun handleClick(x: Int, y: Int) {
        recolorRectangle(x, y)
    }

    /** Changes the fill color of the rectangle containing ([x], [y]). */
    private fun recolorRectangle(x: Int, y: Int) {
        // You will need to determine the boundaries of the rectangle the
        // user has clicked on.
        // You may find the method canvas.getColorAt(x, y) helpful.
        // val sectionColor = canvas.getColorAt(x, y)
        // val containingRectangle = canvas.

        // doesn't change the color if a line is clicked on
        if (canvas.getColorAt(x, y) != Color.BLACK) {
            var xPointer = x
            var widthPointer = x
            var yPointer = y
            var heightPointer = y
            // Move left until hitting a black line or reaching the boundary
            while (xPointer >= 0 && canvas.getColorAt(xPointer, y) != Color.BLACK) {
                xPointer--
            }

            // Move right until hitting a black line or reaching the boundary
            while (widthPointer < REQUESTED_CANVAS_WIDTH &&
                    canvas.getColorAt(widthPointer, y) != Color.BLACK) {
                widthPointer++
            }

            // Move up until hitting a black line or reaching the boundary
            while (yPointer >= 0 && canvas.getColorAt(x, yPointer) != Color.BLACK) {
                yPointer--
            }

            // Move down until hitting a black line or reaching the boundary
            while (heightPointer < REQUESTED_CANVAS_HEIGHT &&
                    canvas.getColorAt(x, heightPointer) != Color.BLACK) {
                heightPointer++
            }

            val colors: List<Color> = listOf(Color.WHITE, Color.BLUE, Color.RED, Color.YELLOW)
            var newColor = canvas.getColorAt(x, y)

            while (newColor == canvas.getColorAt(x, y)) {
                newColor = colors[random.nextInt(0, colors.size)]
            }

            canvas.drawRectangle(
                    xPointer,
                    yPointer,
                    widthPointer - xPointer,
                    heightPointer - yPointer,
                    fillColor = newColor,
                    outlineColor = Color.BLACK
            )
        }
    }
}

/** Creates a canvas and paints it in the style of Piet Mondrian. */
fun main() {
    require(REQUESTED_CANVAS_HEIGHT >= 4 * MIN_RECTANGLE_HEIGHT)
    require(REQUESTED_CANVAS_WIDTH >= 4 * MIN_RECTANGLE_WIDTH)
    MondrianPainter()
}
