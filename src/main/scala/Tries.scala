import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}
import scala.collection.mutable
import scala.collection.Searching._

/** Solution to https://www.hackerrank.com/challenges/ctci-contacts */
class Tries(inStream: InputStream) {
  val contacts: mutable.ArrayBuffer[String] = mutable.ArrayBuffer.empty[String]

  val inputStreamReader = new InputStreamReader(inStream)
  val bufferedReader = new BufferedReader(inputStreamReader)

  val lineCount: Int = bufferedReader.readLine.toInt

  1 to lineCount foreach { _ =>
    val line: String = bufferedReader.readLine
    line.split(" ") match {
      case Array("add", name: String) =>
        val pos: Int = {
          val insertionPoint = contacts.search(name).insertionPoint
          if (insertionPoint < 0) math.abs(insertionPoint) - 1 else insertionPoint
        }
        contacts.insert(pos, name) // insert into sorted position

      case Array("find", prefix: String) =>
        val insertionPoint = contacts.search(prefix).insertionPoint
        if (insertionPoint < 0) 0 else {
          val subArray = contacts.slice(insertionPoint, contacts.length)
          val result = subArray.takeWhile(_.startsWith(prefix)).size
          println(result)
        }
    }
  }
}

object Tries extends App {
  new Tries(new FileInputStream("src/main/resources/tries.data"))
//  new Tries(System.in)
}
