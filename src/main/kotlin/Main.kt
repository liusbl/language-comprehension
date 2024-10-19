import com.google.gson.Gson
import com.google.gson.JsonObject
import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    val gson = Gson()
    Files.walk(Path("letters")).toList().drop(1).forEach { path ->
        val file = path.toFile()
        println(file)
        val text = file.readText()
        val json = gson.fromJson(text, JsonObject::class.java)
        val list = json["details"].asJsonObject["list"].asJsonArray
        println(list[0])
    }
}
