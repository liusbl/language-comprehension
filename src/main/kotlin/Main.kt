import java.io.File

/**
 * Lithuanian:
 * Source: https://ekalba.lt/bendrines-lietuviu-kalbos-zodynas
 * Total sum (including first caps words): 71449
 * Without first caps words: 68485
 * Without duplicates: 67163
 * [USING THIS] Without hyphens: 67091
 *
 * English:
 * Source: https://github.com/dwyl/english-words?tab=readme-ov-file
 * [USING THIS] Sum (without caps words or non-letters): 341122
 */
fun main() {
    val resultFile = File("english-words-all-results.txt")
    if (!resultFile.exists()) {
        resultFile.createNewFile()
    }
    val wordResultList = resultFile.readLines().map(::WordResult)
    val wordList = File("english-words-all.txt").readLines()
    val shuffledWordList = wordList.filter { word -> !wordResultList.map { it.word }.contains(word) }
        .shuffled()

    println("Hello!")
    val comprehensionText = Comprehension.entries.joinToString(separator = ", ") { comprehension ->
        "${comprehension.text}: ${comprehension.shortcut}"
    }
    var currentSessionWords = 0
    shuffledWordList.take(1000).forEach { word ->
        println("----------------------------")
        println(
            "Analysed word count: ${wordResultList.size + currentSessionWords}/${wordList.size}, " +
                    "${(wordResultList.size + currentSessionWords) / wordList.size.toFloat()}%"
        )
        println("Next word: $word")
        println(comprehensionText)
        val input = readln()
        val comprehension = Comprehension(input[0])

        if (comprehension == null) {
            println("Error encountered. Exiting, press Enter")
            readln()
            return
        } else {
            resultFile.appendText("$word;${comprehension.percent}\n")
            currentSessionWords++
        }
    }
}

data class WordResult(
    val word: String,
    val comprehension: Comprehension
)

fun WordResult(line: String): WordResult {
    val (word, percent) = line.split(';')
    return WordResult(word, Comprehension(percent.toInt())!!)
}

enum class Comprehension(
    val text: String,
    val shortcut: Char,
    val percent: Int
) {
    Unheard("Unheard", 'U', 0),
    HeardButUnknown("HeardButUnknown", 'H', 25),
    Doubtful("Doubtful", 'D', 50),
    Passable("Passable", 'P', 75),
    Mastered("Mastered", 'M', 100);
}

fun Comprehension(percent: Int): Comprehension? = Comprehension.entries.find { it.percent == percent }

fun Comprehension(shortcut: Char): Comprehension? = Comprehension.entries.find {
    it.shortcut.equals(shortcut, ignoreCase = true)
}