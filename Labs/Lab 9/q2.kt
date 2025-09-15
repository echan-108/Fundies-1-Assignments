// QUESTION 2
class MenuItem(val isVegetarian: Boolean, val isGlutenFree: Boolean, val isVegan: Boolean)

fun canEatThere(menu: List<MenuItem>, criterion: (MenuItem) -> Boolean): Boolean {
    // criterion literal looks something like this
    // {it: MenuItem -> it.isVegetarian}
    // you can pass parameters into this inline,
    // that is what the parenthesiss mean in the criterion parameter
    // that makes sense
    return menu.any { criterion(it) }
}
