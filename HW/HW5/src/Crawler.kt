import java.net.URI
import kotlin.collections.mutableSetOf

// Congratulations! You have been invited to do an online assessment for a
// stealth company looking to create a new search engine. They are asking you to
// prototype a:
//    * web crawler, which fetches pages from a network and follows the links to
//      connected pages
//    * indexer, which creates an index from individual words to pages that have
//      those words
//    * retrieval engine, which determines which pages best answer a user's
//       query
//    * snippet generator, giving a preview of the search results
//
// They don't want anyone to know they are working on a search engine, so they
// are asking you to fetch pages from a simulated network (provided in
// SupportCode.kt), which returns pages stored in web.txt.
//
// 0. Watch the videos "Homework 5: Introduction" and "Homework 5: Overview"
//    to get the big picture. Look over the provided code, documentation, and
//    text file. You should look at the KDoc in SupportCode.kt but need not
//    concern yourself with the implementation. Do not modify SupportCode.kt.
//
// 1. "Stop words" are words that are so common that search engines don't
//    include them in indices. Research stop words online and change thestopWor
//    initialization of stopWords so it is a set of 20 words, all lower-case,
//    with no punctuation. Be sure to include your source of stop words in the
//    summary.
// [https://productresources.collibra.com/docs/collibra/latest/Content/Settings/ServicesConfiguration/co_stop-words.htm]
//
// 2. Prepare for writing addToIndex() by watching the video "Homework 5:
//    Crawling and Indexing", watching the testAddToIndex() chapter of the video
//    "Homework 5: Tests", and reading CrawlerTests.kt through testAddToIndex().
//    If it is not clear to how how addToIndex() should behave, please talk to
//    classmates or go to office hours. Once you understand the desired behavior,
//    implement addToIndex() and make sure the tests pass. You don't need to
//    write additional tests.
//
// 3. Prepare for writing crawl() by reviewing prior videos as needed, watching
//    the testCrawl() chapter of the video "Homework 5: Tests" and reading
//    testCrawl() in CrawlerTests.kt. Once you understand the desired behavior,
//    implement crawl(). Uncomment testCrawl() from runTests() and make sure all
//    tests pass before proceeding.
//
// 4. Prepare for writing makeSnippet() by reading its KDoc and testMakeSnippet().
//    After implementing makeSnippet(), uncomment testMakeSnippet from runTests()
//    and make sure all tests pass.
//
// 5. Prepare for writing getSearchResults() by reading its KDoc and reviewing
//    the overview video, if that would be helpful.
//
// 6. Test the whole system by calling simulateWebServer(), which is implemented
//    in SupportCode.kt but can be called from this file's main() method. Turn
//    in a transcript showing the results for queries for "still" and "poetry".
//
// 7. Optional challenge: Implement scoring for web pages, where you
//    preferentially return web pages in which more search terms appear (or in
//    which the search terms appear more times). If you do this, you should
//    create a method named rankWebPages() in SearchServer and call it from
//    getSearchResults().
//
// SUMMARY OF CHANGES
//
// The only methods you are required to entirely write are:
// - addToIndex()
// - crawl()
// - makeSnippet()
//
// You are required to complete the implementation of:
// - getSearchResults()
// Do not replace the bits of code that were provided for you.
//
// You are not required to write any KDoc.
//
// The only non-method functions you are required to modify are:
// - runTests() [to call code that is currently commented out]
// - main()     [to call runTests() and simulateWebServer()]
//
// You may add tests to the existing functions (or add new test functions)
// if it helps you debug your code, but you are not required to and will
// not get extra credit.
//
// You may add "helper" functions, such as retainLettersAndSpaces() from class.
// These do not require KDoc.

class Crawler {
    val stopWords =
        setOf(
            "a, and, an, are, as, at, be, but, by, for, if, in, into, is, it, no, not, of, on, or, such, that, their, then, there, these, they, this, to, was, will, with"
        )
    val visited = mutableSetOf<URI>() // pages that have already been retrieved
    val index = mutableMapOf<String, MutableList<WebPage>>() // maps words to pages having the word

    private val network = Network() // use this to fetch pages

    /**
     * Fetches the page with the given [uri], storing information about it so it can be returned in
     * a web search. If the page has already been visited on a previous call, it will not be fetched
     * again.
     */
    fun crawl(uri: URI) {
        // If the URI has already been visited, return without doing anything.
        if (visited.contains(uri)) return
        // Fetch the web page using the network property (defined above).
        val current = network.fetch(uri)
        // Add the URI to visited so we don't unintentionally revisit it.
        visited.add(uri)
        // Return prematurely if fetch returned null rather than a real web page.
        // This simulates a dead link.
        if (current == null) return
        // Call addToIndex() with that page.

        addToIndex(current)
        for (link in current.links) {
            crawl(link)
        }
    }

    /** Add page [page] to the index. */
    fun addToIndex(page: WebPage) {
        // Create a copy of the text on the page, where:
        // - all characters have been removed, except for spaces, newline
        //   characters, and English letters
        // - all letters have been converted to lowercase
        //
        // cleans up the text in the Webpage
        var parsedPage = StringBuilder()
        for (c in page.text) {
            if (c.isLetter() || c == ' ' || c == '\n') {
                parsedPage.append(c.lowercase())
            }
        }

        // Split the text into a list of words. Tip: Call split() with two
        // delimiters: a single space and the new line character ("\n"). The
        // split() function lets you pass multiple delimiters.
        //
        var wordList = parsedPage.toString().split(' ', '\n')

        // Eliminate duplicates in the list of words. There are multiple ways
        // to do this, which you can look up. (Learning how to look things up is
        // a valuable skill.)
        wordList = wordList.toSet().toMutableList()
        //
        // For each word that is not a stop word:
        // 1. Look up the word in the index to find out which pages we already
        //    know have the word.
        // 2. If we get back null, that means no pages are known to have that
        //    word. Add an entry to the index with:
        //     - key: the word
        //     - value: a mutable list containing the web page
        // 3. Otherwise, if we got a list of pages known to have the word, add
        //    this page to that list.

        for (word in wordList) {
            if (!stopWords.contains(word)) {
                if (index.containsKey(word)) {
                    index[word]?.add(page)
                } else {
                    index[word] = mutableListOf(page)
                }
            }
        }
    }

    /** Returns a web server built around the index built by this crawler. */
    fun buildServer() = SearchServer(index)
}

/**
 * A web search server.
 * @property index the index
 */
class SearchServer(
    val index: Map<String, List<WebPage>>,
) {
    /**
     * Returns information about web pages matching [searchTerms], which must be one or more words
     * separated by spaces. [IllegalArgumentException] is thrown if any other characters are found.
     */
    fun getSearchResults(searchTerms: String): Set<String> {
        // Throw an IllegalArgument exception if searchTerm contains
        // any characters other than spaces and letters.
        for (c in searchTerms) {
            require(c.isLetter() || c.isWhitespace()) {
                "searchTerm contains any characters other than spaces and letters"
            }
        }

        // Create a version of the search term that converts all
        // letters to lower case.
        val lowerSearchTerms = searchTerms.lowercase()

        // Split the search term into a list of words.
        val lowerSearchTermsList = lowerSearchTerms.split(' ')
        // Create an empty mutable set of WebPage.
        val matchingPages = mutableSetOf<WebPage>()
        // For each word, use the index to find web pages containing that word.
        // Add all of those pages to matchingPages.
        for (word in lowerSearchTermsList) {
            if (index.containsKey(word)) {
                val pages = index[word]
                if (pages != null) {
                    for (page in pages) {
                        matchingPages.add(page)
                    }
                }
            }
        }
        // Create a set of search results.
        val searchResults = mutableSetOf<String>()
        // Create snippets for every web page.
        for (page in matchingPages) {
            searchResults.add(
                "${page.title} \n${page.uri} \n${page.makeSnippet(searchTerms.split(' '))}"
            )
        }

        return searchResults
    }
}

const val MAX_SNIPPET_LENGTH = 80

/**
 * A web page, with sufficient detail to be returned by a search server.
 * @property uri the URI
 * @property title the title
 * @property text the content of the page
 */
class WebPage(
    val uri: URI,
    val title: String,
    val text: String,
) {
    var links = mutableListOf<URI>()

    /**
     * Returns a snippet of a web page of no more than [MAX_SNIPPET_LENGTH] characters containing at
     * least one of the words in [searchWords], which must be all lower-case.
     *
     * This throws [IllegalArgumentException] if:
     * * [searchWords] is empty
     * * any of the words in [searchWords] are longer than [MAX_SNIPPET_LENGTH]
     * * none of the words in [searchWords] appears in this page
     * * any of the words in [searchWords] contain characters that are not lower-case letters in the
     * English alphabet
     */
    fun makeSnippet(searchWords: List<String>): String {
        // You must implement the checks described in the KDoc.
        var result: String = ""

        require(searchWords.isNotEmpty()) { "searchWords is empty" }
        for (word in searchWords) {
            require(word.length <= MAX_SNIPPET_LENGTH) { "word in searchWords is too long" }
            require(text.lowercase().contains(word)) { "none of these words are on this page" }
            for (c in word) {
                require(c.isLetter() && c.isLowerCase()) {
                    "word contains characters that are not lower-case letters in the English alphabet"
                }
            }
        }
        // Note that the words in searchWords will be lower-case but might have
        // a different case in the document. The snippet must contain text
        // capitalized the same way it is in the initial document (the text
        // property).
        for (word in searchWords) {
            if (text.lowercase().contains(word)) {
                val start = text.lowercase().indexOf(word)
                result = ("${text.substring(start, start + MAX_SNIPPET_LENGTH - 4)}...")
                break
            }
        }
        return result // replace this code and comment
    }

    /**
     * Creates information about a search result consisting of the [title] and [uri] of this page
     * and a snippet showing where it contains one of [searchWords].
     */
    fun makeSearchResult(searchWords: List<String>): String =
        "$title\n$uri\n${makeSnippet(searchWords)}"
}

fun runTests() {
    testAddToIndex()
    testCrawl()
    testMakeSnippet()
    println("All tests passed")
}

fun main() {
    runTests()
    simulateWebServer()
}
