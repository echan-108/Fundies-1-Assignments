import kotlin.system.exitProcess
import masecla.reddit4j.client.Reddit4J
import masecla.reddit4j.client.UserAgentBuilder
import masecla.reddit4j.objects.RedditComment
import masecla.reddit4j.objects.RedditPost
import masecla.reddit4j.objects.subreddit.RedditSubreddit

// These are used for creating the user agent.
// You should change AUTHOR.
private const val APP_NAME = "Fundies Homework 11"
private const val AUTHOR = "CHAN"
private const val VERSION = "0.1"

/** A connection to the Reddit API for a specific user. */
object Connection {
    // Students don't need to worry about how these two statements work.
    private val userAgent =
        UserAgentBuilder().appname(APP_NAME).author(AUTHOR).version(VERSION).build()
    private val redditClient: Reddit4J = Reddit4J.rateLimited()

    init {
        redditClient.apply {
            username = USERNAME
            password = PASSWORD
            clientId = CLIENT_ID
            clientSecret = CLIENT_SECRET
            this.setUserAgent(userAgent)
            try {
                this.connect()
            } catch (e: masecla.reddit4j.exceptions.AuthenticationException) {
                println("The credentials are invalid: ${e.message}")
                exitProcess(1)
                // I think this is the network error, I'm not sure though
            } catch (e: java.net.UnknownHostException) {
                println("The network is probably disconnected, please run again ")
                exitProcess(1)
            }
        }
    }

    /** This user's name (from their profile). */
    val userName: String
        get() = redditClient.selfProfile.name

    /** Gets the subreddit named [subredditName]. */
    fun getSubreddit(subredditName: String): RedditSubreddit {
        return redditClient.getSubreddit(subredditName)
    }

    /**
     * Gets posts from [subreddit].
     *
     * @return text posts that are marked as being acceptable for people under 18
     */
    fun getPosts(subreddit: RedditSubreddit): List<RedditPost> =
        subreddit.hot.submit().filter { !it.isOver18 }.filter { it.selftext.isNotEmpty() }

    /** Gets all comments for this [post]. */
    fun getComments(post: RedditPost): List<RedditComment> {
        return redditClient.getCommentsForPost(post.subreddit, post.id).submit()
    }
}

/**
 * An option to present to the user.
 *
 * @property text a textual description
 * @property function the function to call if the option is selected
 */
class Option(val text: String, val function: () -> Unit) {
    companion object {
        /**
         * Offers the user [options] of what to do next. In addition to showing the passed
         * options, there is always an option numbered 0 to quit the program and a final
         * option to select a subreddit.
         */
        fun offerOptions(options: List<Option>) {
            val allOptions =
                listOf(Option("Quit", function = { exitProcess(0) })) +
                        options +
                        listOf(
                            Option(
                                "Select a subreddit",
                                function = { selectSubreddit() }
                            )
                        )
            println("Select an option: ")
            for (i in allOptions.indices) {
                println("\t$i. ${allOptions[i].text}")
            }

            // take input, see if it is empty if so, give message and reprompt
            // check if it is an integer, if not give message and reprompt
            // check if it is in the range, if not give message and reprompnt

            try {
                val input = readln().toInt()
                allOptions[input].function()
            } catch (e: NumberFormatException) {
                println("Input was not a number")
                println("Input must be a number in the range")
                offerOptions(options)
            } catch (e: IndexOutOfBoundsException) {
                println("Input was not in range")
                println("Input must be a number in the range")
                offerOptions(options)
            }
        }

        private fun showPostAuthor(posts: List<RedditPost>, postNumber: Int) {
            println("Post author: ${posts[postNumber].author}")
            val options = mutableListOf<Option>()
            // plust 2 because of zero based indexing
            if (posts.size > postNumber + 1) {
                options.add(
                    Option(
                        "Show next post",
                        function = { showPost(posts, postNumber + 1) }
                    ),
                )
            }
            offerOptions(
                listOf(
                    Option(
                        "Show post again",
                        function = { showPost(posts, postNumber) }
                    ),
                    Option(
                        "Check for comments",
                        function = { checkForComments(posts, postNumber) }
                    ),
                ) +
                        options
            )
        }

        private fun checkForComments(posts: List<RedditPost>, postNumber: Int) {
            val options =
                mutableListOf(
                    Option(
                        "Show post author",
                        function = { showPostAuthor(posts, postNumber) }
                    ),
                )
            if (posts.size > postNumber + 1) {
                options.add(
                    Option(
                        "Show next post",
                        function = { showPost(posts, postNumber + 1) }
                    ),
                )
            }
            val comments: List<RedditComment> =
                Connection.getComments(posts[postNumber])
            println(
                when (comments.size) {
                    0 -> "There are no comments for this post."
                    1 -> "There is one comment for this post."
                    else -> "There are ${comments.size} comments for this post."
                }
            )
            if (comments.isNotEmpty()) {
                options.add(
                    0,
                    Option(
                        "Show first comment",
                        function = {
                            showComment(posts, postNumber, comments, 0)
                        }
                    )
                )
            }
            offerOptions(options)
        }

        private fun displayPost(post: RedditPost) {
            println(post.title.uppercase())
            println()
            println(post.selftext)
            println()
        }

        private fun showPost(posts: List<RedditPost>, postNumber: Int) {
            displayPost(posts[postNumber])
            val options = mutableListOf<Option>(
                Option(
                    "Show post author",
                    function = { showPostAuthor(posts, postNumber) }
                ),
                Option(
                    "Check for comments",
                    function = { checkForComments(posts, postNumber) }
                ),
            )
            if (postNumber + 1 < posts.size) {
                options.add(
                    Option(
                        "Show next post",
                        function = { showPost(posts, postNumber + 1) }
                    ),
                )
            }
            offerOptions(
                options
            )
        }

        private fun showComment(
            posts: List<RedditPost>,
            postNumber: Int,
            comments: List<RedditComment>,
            commentNumber: Int
        ) {
            println(comments[commentNumber].body)
            val options = mutableListOf<Option>(
                Option(
                    "Show comment author",
                    function = { showCommentAuthor(posts, postNumber, comments, commentNumber) }
                ),
                Option(
                    "Show post again",
                    function = { showPost(posts, postNumber) }
                )
            )


            if (comments.size > commentNumber + 1) {
                options.add(
                    Option(
                        "Show next comment",
                        function = { showComment(posts, postNumber, comments, commentNumber + 1) }
                    )
                )
            }

            if (posts.size > postNumber + 1) {
                options.add(
                    Option(
                        "Show next post",
                        function = { showPost(posts, postNumber) }
                    )
                )
            }
            offerOptions(options)
        }

        private fun showCommentAuthor(
            posts: List<RedditPost>,
            postNumber: Int,
            comments: List<RedditComment>,
            commentNumber: Int
        ) {
            println(comments[commentNumber].author)
            val options = mutableListOf<Option>(
                Option(
                    "Show comment author again",
                    function = { showCommentAuthor(posts, postNumber, comments, commentNumber) }
                ),
                Option(
                    "Show post again",
                    function = { showPost(posts, postNumber) }
                )
            )
            if (comments.size > commentNumber + 1) {
                options.add(
                    Option(
                        "Show next comment",
                        function = { showComment(posts, postNumber, comments, commentNumber + 1) }
                    )
                )
            }

            if (posts.size > postNumber + 1) {
                options.add(
                    Option(
                        "Show next post",
                        function = { showPost(posts, postNumber) }
                    )
                )
            }
            offerOptions(options)

        }

        private fun quit() {
            println("Goodbye.")
            exitProcess(0)
        }

        private fun selectSubreddit() {
            println("What subreddit would you like to select? ")
            while (true) {
                try {
                    val subredditName = readln()
                    val subreddit: RedditSubreddit = Connection.getSubreddit(subredditName)
                    println("You are now in ${subreddit.displayName}.")
                    val posts = Connection.getPosts(subreddit)
                    when (posts.size) {
                        0 -> println("There are no posts here")
                        1 -> println("There is one post")
                        else -> println("There are ${posts.size} posts.")
                    }

                    // checks to see if first post is a valid option
                    val options = mutableListOf<Option>()
                    if (posts.isNotEmpty()) {
                        options.add(
                            Option(
                                "Show First Post",
                                function = { showPost(posts, 0) }
                            )
                        )
                    }
                    offerOptions(
                        options
                    )
                    break
                } catch (e: IllegalArgumentException) {
                    println("That subreddit does not exist, please enter one that does.")
                } catch (e: java.net.SocketException) {
                    println("The network must have been disconnected, please retry")
                } catch (e: java.net.UnknownHostException) {
                    println("The network must have been disconnected, please retry")
                }
            }

        }
    }
}

fun main() {
    println("Hello, ${Connection.userName}.")
    Option.offerOptions(emptyList())
}
