import kotlin.random.Random

class SpiderSpawner() : Entity("SpiderSpawner", "SpawnerOnSand.png"), Damageable {
    val spawnProbability = .25
    private var hardness = 6

    private fun spawnIfSpace() {
        val validCell = selectAdjacentEmptyCell()
        if (validCell != null) {
            Game.place(Spider(), validCell.x, validCell.y)
            Game.addText("A cell was found, a Spider was spawned")
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
        Game.addText("The spawner has been destroyed")
        Game.remove(this)
    }
}
