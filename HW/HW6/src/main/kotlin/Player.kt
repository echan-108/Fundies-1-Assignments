import kotlin.random.Random

/** The player, who is controlled by key presses. */
class Player : LivingEntity("Steve", "Steve.png", 20, 6) {
    var lastKeyPressed: String? = null

    override fun tick() {
        // Use a when-statement to decide what to do based on lastKeyPressed.
        // If it is null, do nothing.
        // If it is arrow-right or d, try to move right.
        // If it is arrow-left or a, try to move left.
        // If it is arrow-up or w, try to move up.
        // If it is arrow-down or s, try to move down.
        // If it is space, do nothing.
        // If it is / (slash), attack all hostile mobs in fighting distance,
        // outputting an appropriate message if there are none.
        // You may find it helpful to use the property Game.placedEntities.

        // Otherwise, output a message saying it couldn't
        // process that character (stating what it was).

        // Finally, set lastKeyPressed to null

        when {
            (lastKeyPressed == "arrow-right" || lastKeyPressed == "d") -> moveRight()
            (lastKeyPressed == "arrow-up" || lastKeyPressed == "w") -> moveUp()
            (lastKeyPressed == "arrow-left" || lastKeyPressed == "a") -> moveLeft()
            (lastKeyPressed == "arrow-down" || lastKeyPressed == "s") -> moveDown()
            (lastKeyPressed == "h") -> hypershift()
            (lastKeyPressed == "/") -> attack()
            (lastKeyPressed == "space") ->
                    Game.addText("Pressing space does nothing!") // Do Nothing
            else -> Game.addText("I don't know how to handle $lastKeyPressed")
        }
        lastKeyPressed = null
    }

    /** Does [attackStrength] damage to all hostile mobs within FIGHTING_RANGE */
    fun attack() {
        for (mob in Game.placedEntities) {
            if (mob is Mob) {
                if (mob.isAggressive && Game.calculateDistance(Game.player, mob) <= FIGHTING_RANGE)
                        mob.takeDamage(Game.player, this.attackStrength)
            }
        }
    }

    /**
     * Teleports the player to a random place on the board and causes death to whoever loses a coin
     * toss if the player happens to colliede with another entity Gives the player more of a fair
     * chance in the case of a collision.
     */
    fun hypershift() {
        val xCoord = Random.nextInt(0, 9)
        val yCoord = Random.nextInt(0, 9)

        if (Game.isOccupied(xCoord, yCoord)) {
            val coinToss = Random.nextInt(0, 1)
            if (coinToss == 1) {
                Game.addText("Player has prevailed")
                val occupyingMob = Game.getEntity(xCoord, yCoord)
                requireNotNull(occupyingMob)
                Game.remove(occupyingMob)
                Game.place(this, xCoord, yCoord)
            } else {
                Game.addText("Player has failed to prevail")
                this.die()
            }
        } else {
            Game.place(this, xCoord, yCoord)
        }
        // use kotlin random to get an x and y coord
        // see if space is occupied
        // if so use kotlin random to flip a coin, (1 or 0)
        // if kill player on zero, kill other entity on one
        // place player at end if it wins coin toss
    }
}
