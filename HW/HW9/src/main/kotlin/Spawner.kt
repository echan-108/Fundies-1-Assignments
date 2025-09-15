import kotlin.random.Random

/** A spawner of any type of [Entity]. */
open class Spawner<T : Entity>(
        private val spawnType: String,
        private val spawn: () -> T,
        private val spawnProbability: Double,
        private var hardness: Int
) : Entity("$spawnType Spawner", "SpawnerOnSand.png"), Damageable {

    private fun spawnIfSpace() {
        val validCell = selectAdjacentEmptyCell()
        if (validCell != null) {
            Game.place(spawn(), validCell.x, validCell.y)
            Game.addText("A cell was found, a ${spawnType} was spawned")
        } else {
            Game.addText("Nothing could be spawned, as there are no adjacent tiles")
        }
    }

    override fun tick() {
        val rng = Random.nextDouble(0.0, 1.0)
        if (rng <= spawnProbability) {
            spawnIfSpace()
        }
        Game.addText("The random number was ${rng}")
    }

    override fun takeDamage(damage: Int) {
        hardness -= damage
        if (hardness <= 0) {
            destroySelf()
        }
    }

    private fun destroySelf() {
        Game.addText("The ${spawnType} spawner has been destroyed")
        Game.remove(this)
    }
}
