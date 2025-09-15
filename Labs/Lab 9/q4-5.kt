// used ExpandedPerson so I didn't have to edit the other person class
class ExpandedPerson(
        var name: String,
        var age: Int,
        val friends: Set<ExpandedPerson>,
        var hasLadder: Boolean
) {
    // QUESTION 4
    fun getFriendWithLadder(): ExpandedPerson? {
        // use filter function, which is filter in
        // return list or the filtered thing
        // use first or null function
        return friends.filter { friend -> friend.hasLadder }.firstOrNull()
    }

    // QUESTION 5
    fun getFriendOfFriendWithLadder(): ExpandedPerson? {
        // val friendsOfFriends = friends.map { friend -> friend.friends }
        // flatten gotten from kotlin docs
        // val friendsOfFriendsExploded = friendsOfFriends.flatten()

        // made it all one line
        if (getFriendWithLadder() != null) {
            // checking freidns first
            return getFriendWithLadder()
        } else {
            return friends
                    .map { friend -> friend.friends } // stores a list of of the friends friends
                    .flatten() // turns the "matrix" into a long list
                    .filter { friend -> friend.hasLadder } // checks for ladderedness
                    .firstOrNull() // returns first or null
        }
    }
}
