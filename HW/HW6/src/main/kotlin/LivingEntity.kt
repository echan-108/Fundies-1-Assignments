import kotlin.random.Random

val FIGHTING_RANGE = 3.0

/** A living entity, such as a [Mob] or [Player] in the game. */
abstract class LivingEntity(
        type: String,
        imageFileName: String,
        val maxHearts: Int,
        val attackStrength: Int,
) : Entity(type, imageFileName) {
    enum class Status {
        Healthy,
        Injured,
        Dead
    }

    var numHearts = maxHearts
        private set

    var status = Status.Healthy
        private set
        get() =
                when (numHearts) {
                    maxHearts -> Status.Healthy
                    0 -> Status.Dead
                    else -> Status.Injured
                }

    val isAlive
        get() = status != Status.Dead

    override fun toString() = "$status $type"

    /**
     * Takes up to [damage] hearts of damage, to a maximum of [numHearts], printing a message with
     * the amount of damage taken and the resulting [status].
     */
    fun takeDamage(
            attacker: LivingEntity,
            damage: Int,
    ) {
        val actualDamage = if (damage > numHearts) numHearts else damage
        numHearts -= actualDamage
        val text = if (actualDamage == 1) "heart" else "hearts"
        Game.addText("$type took $actualDamage $text of damage and is now $status.")
        if (status == Status.Dead) {
            this.die()
        }
    }

    /** Attacks [victim], doing [attackStrength] hearts of damage. */
    open fun attack(victim: LivingEntity) {
        Game.addText("$type attacked ${victim.type}.")
        victim.takeDamage(this, attackStrength)
    }

    /**
     * Moves the entity int[x] cells right and int[y] cells up negative numbers move the entity left
     * and down respectivly
     */
    fun move(deltaX: Int, deltaY: Int) {
        // gets entity's position
        val currentPos = Game.getPosition(this)
        if (currentPos == null) {
            throw IllegalArgumentException("Current position cannot be null")
        } // error message is probably moot

        val currentX = currentPos.x
        val currentY = currentPos.y

        if (!Game.isInBounds(currentX + deltaX, currentY - deltaY)) {
            // do nothing
        } else if (Game.isOccupied(currentX + deltaX, currentY - deltaY)) {
            // do nothing
        } else {
            Game.place(this, currentX + deltaX, currentY - deltaY)
        }
    }

    /**
     * Moves right one cell, or stays in the same place, if that cell is occupied by a
     * [LivingEntity] or out of bounds. This should throw [IllegalArgumentException] if this living
     * entity is not on the board.
     */
    fun moveRight() {
        // You should call Game.getPosition() and Game.place() as part of
        // your implementation.
        move(1, 0)
    }

    /**
     * Moves left one cell, or stays in the same place, if that cell is occupied by a [LivingEntity]
     * or out of bounds.
     */
    fun moveLeft() {
        move(-1, 0)
    }

    /**
     * Moves up one cell, or stays in the same place, if that cell is occupied by a [LivingEntity]
     * or out of bounds.
     */
    fun moveUp() {
        move(0, 1)
    }

    /**
     * Moves down one cell, or stays in the same place, if that cell is occupied by a [LivingEntity]
     * or out of bounds.
     */
    fun moveDown() {
        move(0, -1)
    }

    /**
     * Moves randomly to an adjacent unoccupied cell. Cells are considered adjacent if they are to
     * the right, left, up, or down (i.e., changing by 1 either the x-coordinate or the y-coordinate
     * but not both).
     */
    fun moveRandomly() {
        val rng = Random.nextInt(0, 4)
        when {
            rng == 0 -> moveUp()
            rng == 1 -> moveLeft()
            rng == 2 -> moveDown()
            rng == 3 -> moveRight()
        }
    }

    /**
     * Tries to move to an adjacent cell closer to [player]. Cells are considered adjacent if they
     * are to the right, left, up, or down (i.e., changing by 1 either the x-coordinate or the
     * y-coordinate but not both). If all closer adjacent cells are occupied, no movement will
     * occur. This should throw [IllegalArgumentException] if this living entity or [player] is not
     * on the board.
     */
    fun moveTowards(player: Player) {
        if (Game.getPosition(this) == null || Game.getPosition(player) == null) {
            throw IllegalArgumentException("Player or Entity must be on board")
        }
        if (Game.calculateDistance(this, player) <= FIGHTING_RANGE) {
            attack(player)
        } else {
            // move towards player
            // gets positions of both objects
            val playerPos = Game.getPosition(player)
            val thisPos = Game.getPosition(this)
            requireNotNull(thisPos)
            requireNotNull(playerPos)

            if (thisPos.x - playerPos.x > 0) {
                // this means that player is to the left
                this.moveLeft()
            } else if (thisPos.x - playerPos.x < 0) {
                // this means that the player is to the right
                this.moveRight()
            } else {
                // this means that the player is on the same y axis
                if (thisPos.y - playerPos.y > 0) {
                    // this means that the player is above
                    this.moveUp()
                } else {
                    // this means that the player is below
                    this.moveDown()
                }
            }
        }
    }
}
