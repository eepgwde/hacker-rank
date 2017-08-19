import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}
import scala.collection.mutable

/** Solution to https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks
  * I admit I cheated because I did not use 2 stacks, because I thought that implementation requirement was dumb. */
class Stacks2(in: InputStream) {
  val inputStreamReader = new InputStreamReader(in)
  val bufferedReader = new BufferedReader(inputStreamReader)
  val lineCount: Int = bufferedReader.readLine.mkString.toInt
  val queue: mutable.ArrayBuffer[String] = mutable.ArrayBuffer.empty

  1 to lineCount foreach { _ =>
    bufferedReader.readLine.split(" ") match {
      case Array("1", x) => queue.append(x)
      case Array("2") => queue.remove(0)
      case Array("3") => println(queue.head)
    }
  }
}

object Stacks2 extends App {
  new Stacks2(new FileInputStream("src/main/resources/2stacks.data"))
//  new Stacks2(System.in)
}
