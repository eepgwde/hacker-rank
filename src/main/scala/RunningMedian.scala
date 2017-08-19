import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util
import java.util.Scanner
import scala.collection.Searching._

/** Solution to https://www.hackerrank.com/challenges/ctci-find-the-running-median */
class RunningMedian(inStream: InputStream) {
  val data: util.List[java.lang.Integer] = new util.ArrayList[java.lang.Integer]()

  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val lineCount: Int = in.nextInt()

  1 to lineCount foreach { _ =>
    val int: java.lang.Integer = in.nextInt()
    val pos: Int = {
      val p = util.Collections.binarySearch(data, int)
      if (p < 0) Math.abs(p) - 1 else p
    }
    data.add(pos, int)
    println(f"${ median(data) }%.1f")
  }

  def average(a: Int, b: Int): Double = (a + b) / 2.0

  def isEven(i: Int): Boolean = (i % 2) == 0
  def isOdd(i: Int): Boolean = (i % 2) == 1

  def median(data: util.List[Integer]): Double = {
    if (isOdd(data.size))
      data.get(data.size / 2).toDouble
    else {
      val midSize = data.size / 2
      (data.get(midSize-1) + data.get(midSize)) / 2.0
    }
  }
}

object RunningMedian extends App {
  new RunningMedian(new FileInputStream("src/main/resources/runningMedian.data"))
//  new RunningMedian(System.in)
}
