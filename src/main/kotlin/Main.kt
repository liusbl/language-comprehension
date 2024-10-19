import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

/**
 * Total sum (including first caps words): 71449
 * Without first caps words: 68485
 * Without duplicates: 67163
 * Without hyphens: 67091
 */
fun main() {
    val gson = Gson()
    val resultFile = File("lithuanian-words.txt")
    resultFile.delete()
    Files.walk(Path("letters")).toList().drop(1).forEach { path ->
        val file = path.toFile()
        println(file)
        val text = file.readText()
        val json = gson.fromJson(text, JsonObject::class.java)
        val list = json["details"].asJsonObject["list"].asJsonArray

        val wordText = list.map { it.asJsonObject["header"].asString }
            .filter { word -> word[0].isLowerCase() }
            .filter { word -> !word.contains("-") }
            .distinct()
            .joinToString(separator = "\n")
        resultFile.appendText(wordText)
    }
}
