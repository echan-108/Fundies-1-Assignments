// QUESTION 3
fun canEatTogether(menu: List<MenuItem>, criteria: List<(MenuItem) -> Boolean>): Boolean {
    // return true if there is an item for each criteria in the list
    // start with each item in the menu item list and check if all is applicable
    // inside this, check if there is an item that matches the criteria
    return criteria.all { criterion -> menu.any { menuItem -> criterion(menuItem) } }
}
