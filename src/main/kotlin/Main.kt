import java.io.File
import java.util.*
import kotlin.math.round

/**
 * English (all words):
 * Source: https://github.com/dwyl/english-words?tab=readme-ov-file
 * [USING THIS] Sum (without caps words or non-letters): 341122
 *
 * English (12 dict):
 * Source: http://wordlist.aspell.net/12dicts/. 12dicts-6.0.2.zip (ver 6.0.2)
 * README: http://wordlist.aspell.net/12dicts-readme/
 *
 * Lithuanian:
 * Source: https://ekalba.lt/bendrines-lietuviu-kalbos-zodynas
 * Total sum (including first caps words): 71449
 * Without first caps words: 68485
 * Without duplicates: 67163
 * [USING THIS] Without hyphens: 67091
 */

private const val ENGLISH_ALL_WORD_FILE_NAME = "english-words-all"
private const val ENGLISH_12DICT_WORD_FILE_NAME = "english-words-12dict" // TODO
private const val LITHUANIAN_WORD_FILE_NAME = "lithuanian-words"

fun main() {
    println("Hello! Choose language to check.")
    println("English (341k): E. Lithuanian (67k): L.")
    val language = readln().lowercase(Locale.getDefault())
    val fileName = when (language[0]) {
        'e' -> ENGLISH_ALL_WORD_FILE_NAME
        'l' -> LITHUANIAN_WORD_FILE_NAME
        else -> throw Exception("Unexpected language: $language")
    }

    val resultFile = File("$fileName-results.txt")
    if (!resultFile.exists()) {
        resultFile.createNewFile()
    }
    val wordResultList = resultFile.readLines().map(::WordResult)
    val wordList = File("$fileName.txt").readLines()
    val shuffledWordList = wordList.filter { word -> !wordResultList.map { it.word }.contains(word) }
        .shuffled()

    val currentSessionWordResultList = mutableListOf<WordResult>()
    shuffledWordList.take(1000).forEach { word ->
        println("----------------------------")
        val fullList = wordResultList + currentSessionWordResultList
        println(
            "Analysed word count: ${fullList.size}/${wordList.size}, " +
                    "${(fullList.size) / wordList.size.toFloat()}%. " +
                    "Average comprehension: ${(fullList.map { it.comprehension.percent }.average()).roundTo()}%"
        )
        val comprehensionText = Comprehension.entries.joinToString(separator = ", ") { comprehension ->
            val comprehensionListSize = fullList.filter { it.comprehension == comprehension }.size
            "${comprehension.text}: ${comprehension.shortcut} " +
                    "($comprehensionListSize, ${(comprehensionListSize.toFloat() / fullList.size * 100).roundTo()}%)"
        }
        println(comprehensionText)
        println("NEXT WORD: $word")
        val input = readln()
        val comprehension = Comprehension(input[0])

        if (comprehension == null) {
            println("Error encountered. Exiting, press Enter")
            readln()
            return
        } else {
            resultFile.appendText("$word;${comprehension.percent}\n")
            currentSessionWordResultList += WordResult(word, comprehension)
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


fun Double.roundTo(precision: Int = 2): Double {
    var multiplier = 1.0
    repeat(precision) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun Float.roundTo(precision: Int = 2): Float {
    var multiplier = 1f
    repeat(precision) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}