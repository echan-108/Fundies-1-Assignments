import java.io.File

fun main() {
    // val map0 = countCharNGrams("Rasika has a cat. Ellen has a cat. Miguel has a dog.", 4)
    // for (key in map0.keys) {
    //     println("$key: ${map0[key]}")
    // }

    // val map =
    //         nextLetterFrequency(
    //                 " a ",
    //                 countCharNGrams("Rasika has a cat. Ellen has a cat. Miguel has a dog.", 4)
    //         )
    // for (key in map.keys) {
    //     println("$key: ${map[key]}")
    // }

    // val nGramCounts =
    //         countCharNGrams(
    //                 text =
    //                         "… Too alarmin' now to talk about Take your pictures down and shake
    // it out Truth or consequence, say it aloud Use that evidence, race it around … There goes my
    // hero Watch him as he goes There goes my hero He's ordinary … Don't the best of them bleed it
    // out While the rest of them peter out? Truth or consequence, say it aloud Use that evidence,
    // race it around … There goes my hero Watch him as he goes There goes my hero He's ordinary …
    // Kudos, my hero Leavin' all the best You know my hero The one that's on … There goes my hero
    // Watch him as he goes There goes my hero He's ordinary … There goes my hero Watch him as he
    // goes There goes my hero He's ordinary",
    //                 n = 4
    //         )
    // var prompt = "now"
    // print(prompt)
    // for (i in 1..400) {
    //     val nextLetter = generateNextChar(prompt, nGramCounts)
    //     print(nextLetter)
    //     prompt = prompt.substring(1) + nextLetter
    // }

    // QUESTION 5
    val text: String =
            File("/home/dense/Projects/Academic Works/CSFUNDIES/Labs/Lab 5/GUTS/bad_idea_right.txt")
                    .bufferedReader()
                    .use { it.readText() }
    val gramCounts = countCharNGrams(text, 4)
    var prompt = "hea"
    print(prompt)
    for (i in 1..1000) {
        val nextLetter = generateNextChar(prompt, gramCounts)
        print(nextLetter)
        prompt = prompt.substring(1) + nextLetter
    }
}

// QUESTION 1
// assums there are only letters and spaces in the text
fun countWords(text: String): Map<String, Int> {
    val currentWord = StringBuilder()
    val result = mutableMapOf<String, Int>()
    for (char in text) {
        if (char == ' ' || char == text.last()) {
            if (currentWord.isNotEmpty()) {
                val word = currentWord.toString()
                if (result.containsKey(word)) {
                    result[word] = (result[word] ?: 0) + 1
                } else {
                    result[word] = 1
                }
                currentWord.clear()
            }
        } else {
            currentWord.append(char)
        }
    }
    return result
}

// QUESTION 2
fun countCharNGrams(text: String, n: Int): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    for (i in 0..(text.length - 1 - n)) {
        val ngram = text.substring(i, i + n)
        result[ngram] = (result[ngram] ?: 0) + 1
    }
    return result
}

// QUESTION 3
fun nextLetterFrequency(prompt: String, counts: Map<String, Int>): Map<Char, Int> {
    val result = mutableMapOf<Char, Int>()
    for (key in counts.keys) {
        if (key.substring(0, prompt.length) == prompt) {
            val nextChar = key[prompt.length]
            result[nextChar] = (counts[key] ?: 0)
        }
    }
    return result
}

// QUESTION 4
fun generateNextChar(prompt: String, counts: Map<String, Int>): Char {
    val nextLetterFreq = nextLetterFrequency(prompt, counts)
    var max = 'c'
    val keys = nextLetterFreq.keys.toList()
    if (keys.isNotEmpty()) {
        max = keys.random()
    }
    return max
}

// This was a cool lab
