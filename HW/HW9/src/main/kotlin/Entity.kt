import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadImage

/**
 * An animate or inanimate object in the game.
 *
 * @constructor Constructs an entity with the given [type] and [imageFileName].
 */
abstract class Entity(
        protected val type: String,
        imageFileName: String,
) {
    val image: ColorBuffer = loadImage("data/images/$imageFileName")

    /** Takes an action during its turn. */
    abstract fun tick()

    /**
     * Removes this entity from the game. It will no longer appear on the screen, and its [tick]
     * method will no longer be called.
     */
    open fun exit() {
        Game.remove(this)
    }

    /**
     * Returns the position of an empty cell adjacent to this entity or 'null' if no such cell
     * exists or if this entity is not on the board
     */
    fun selectAdjacentEmptyCell(): Position? {
        // return null if the position is off the board
        val currentPos = Game.getPosition(this)
        if (currentPos == null) return null

        // check all adjacent squares and return the first adjacent one
        val currentPosX = currentPos.x
        val currentPosY = currentPos.y
        val xOffsets = listOf(0, 0, -1, 1)
        val yOffsets = listOf(1, -1, 0, 0)

        for (i in 0..3) {
            val resultPosition = Position(currentPosX + xOffsets[i], currentPosY + yOffsets[i])
            if (Game.isEmpty(resultPosition.x, resultPosition.y)) return resultPosition
        }

        // return null if none of the positions were empty
        return null
    }
}
