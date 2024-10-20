import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

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
    val gson = Gson()
    val resultFile = File("english-words.txt")
    resultFile.delete()
    val result = File("letters/words.txt").readLines()
        .filter { word -> !word.contains("-") && !word.contains(".") }
        .filter { it.all { it.isLowerCase() } }
        .distinct()
        .joinToString(separator = "\n")
    resultFile.writeText(result)
}
