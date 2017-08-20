package runningMedian

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.Searching._
import scala.collection.mutable

/** Solution to https://www.hackerrank.com/challenges/ctci-find-the-running-median */
class RunningMedian(inStream: InputStream) {
  // The key to this solution is that this array is maintained in sorted order:
  val data: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer.empty[Int]

  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val lineCount: Int = in.nextInt()

  1 to lineCount foreach { _ =>
    val newItem: Int = in.nextInt()
    val pos: Int = {
      val insertionPoint = data.search(newItem).insertionPoint
      if (insertionPoint < 0) math.abs(insertionPoint) - 1 else insertionPoint
    }
    data.insert(pos, newItem) // insert into sorted position
    println(f"${ median(data) }%.1f")
  }

  def isOdd(i: Int): Boolean = (i % 2) == 1

  def median(data: mutable.ArrayBuffer[Int]): Double = {
    if (isOdd(data.length))
      data(data.length / 2).toDouble
    else {
      val middle = data.length / 2
      (data(middle-1) + data(middle)) / 2.0
    }
  }
}

object Solution extends App {
  new RunningMedian(new FileInputStream("src/main/resources/runningMedian.data"))
//  new RunningMedian(System.in)
}
