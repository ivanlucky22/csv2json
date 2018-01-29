import org.apache.commons.io.FileUtils
import java.io.File
import java.util.ArrayList
import java.util.LinkedHashMap
import org.codehaus.jackson.map.ObjectMapper


fun main(args: Array<String>) {

    val lines = readLines("books.csv")

    val list = ArrayList<Map<String, String>>()
    val obj = LinkedHashMap<String, String>()
    var headers = ArrayList<String>(100)

    lines?.forEachIndexed { index, line ->
        if (index == 0) {
            headers.addAll((line as String).split(","))
        } else {
            val values = (line as String).split(",")
            val pair = LinkedHashMap<String, String>()
            values.forEachIndexed { valueIndex, value ->
                pair.put(headers[valueIndex], value)
            }
            list.add(pair)

        }
    }
    val mapper = ObjectMapper()

    mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, list)


}

fun readLines(s: String): MutableList<Any?> {
    return FileUtils.readLines(File(s), "UTF-8")
}

data class Book(val title: String, val author: String)