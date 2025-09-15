import java.io.File

interface DataReader {
    /**
     * returns a list of the rows of data which fit all criteria specified by the user (as a list of
     * Strings)
     */
    fun getResults(criteria: (String) -> Boolean): List<String>

    /**
     * takes two parameters: the category on which the user would like to filter (e.g., Avg min
     * between sent tnx), and a function / predicate which implements the filter (e.g.,
     * {it.toDouble() < 300.5}). It should combine the two into a single predicate, which it adds to
     * a list of filters on which the user would like to filter the data
     */
    fun addFilter(category: String, predicate: (String) -> Boolean)
}

class DataReaderImplementation(filepath: String) : DataReader {

    private val csvData: List<List<String>> = File(filepath).readLines().map { it.split(",") }
    private val filters = mutableListOf<(List<String>) -> Boolean>()
    private val columnIndices = mutableMapOf<String, Int>()

    override fun getResults(criteria: (String) -> Boolean): List<String> {
        return csvData[0].filter(criteria)
    }

    // Add a filter based on category and predicate
    override fun addFilter(category: String, predicate: (String) -> Boolean) {
        val columnIndex =
                columnIndices[category] ?: throw IllegalArgumentException("Invalid category")
        filters.add { row -> predicate(row[columnIndex]) }
    }
}

fun main() {}
