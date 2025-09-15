package `Lab 2`

data class sandwhich(val bread: Bread, val toppingCount: Int, val toasted: Boolean)

enum class Bread(val name: String) {
    WHEAT("wheat"),
    PUMPERNICKLE("pumpernickel"),
    WHITE("white"),
    RYE("rye"),
    SOURDOUGH("sourdough"),
    ENGLISHMUFFIN("english muffin")
}

val grilledCheese = sandwhich(Bread.WHITE, 1, True)
val turkeyClub = sandwhich(Bread.RYE, 7, False)