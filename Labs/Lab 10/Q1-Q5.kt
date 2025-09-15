// Q1, Q2, Q3, Q4, Q5

import java.awt.Color

// QUESTION 1
interface Clothing {
    val color: Color
    val material: Material

    enum class Material(val nameString: String) {
        DENIM("Denim"),
        WOOL("Wool"),
        COTTON("Cotton"),
        LEATHER("Leather"),
        NYLON("Nylon")
    }
}

class Jeans() : Clothing {
    override val color = Color.BLUE
    override val material = Clothing.Material.DENIM
    override fun toString() = "Jeans"
}

class LeatherJacket() : Clothing {
    override val color = Color.BLACK
    override val material = Clothing.Material.LEATHER
    override fun toString() = "Leather Jacket"
}

// QUESTION 2
class Socks() : Clothing {
    override val color = Color.WHITE
    override val material = Clothing.Material.COTTON
    override fun toString() = "Socks"
}

fun dry(clothes: List<Clothing>) {
    clothes.forEach {
        print("Now Drying ${it.toString()}, ")
        if (it.material == Clothing.Material.COTTON) {
            print("${it.toString()} has shrunk!")
        }
        println()
    }
}

// QUESTION 3
class Shirt<B>(c: Color, val buttonsList: List<B>) : Clothing {
    override val color = c
    override val material = Clothing.Material.COTTON
    override fun toString() = "A shrit with buttons"
}

// QUESTION 4
class ClothingStoreItem(val price: Int, val forAdults: Boolean, val item: Clothing) {
    override fun toString() =
            "${item} made from ${item.material.nameString} which costs \$${(price/100)}.${price%100}"
}

// QUESTION 5
interface Purchasable {
    val price: Int
    val item: Clothing
}

class PurchasableClothingStoreItem(
        override val price: Int,
        val forAdults: Boolean,
        override val item: Clothing
) : Purchasable {
    override fun toString() =
            "${item} made from ${item.material.nameString} which costs \$${(price/100)}.${price%100}"
}

fun main() {
    dry(
            listOf(
                    Jeans(),
                    Jeans(),
                    Jeans(),
                    LeatherJacket(),
                    Jeans(),
                    Socks(),
                    LeatherJacket(),
                    Jeans()
            )
    )

    // a shirt that uses jeans as buttons
    val jeansForButtons = Shirt(Color.WHITE, listOf(Jeans(), Jeans(), Jeans(), Jeans()))

    val clothingStoreShirt = ClothingStoreItem(4025, true, jeansForButtons)
    println(clothingStoreShirt)
}
