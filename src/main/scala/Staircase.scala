package staircase

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.mutable

/** Solution to https://www.hackerrank.com/challenges/ctci-recursive-staircase
  *
  * To run:
  * {{ bin/run staircase.Solution < src/main/resources/staircase.data }} */
class Staircase(inStream: InputStream) {
  val cache = mutable.HashMap.empty[Int, Int]
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val staircaseCount: Int = in.nextInt()

  1 to staircaseCount foreach { _ =>
    cache.clear()
    cache.put(0, 1)
    println(staircase(in.nextInt()))
  }

  def staircase(stairCount: Int): Int = {
    if (stairCount < 0) 0 else
    if (cache.contains(stairCount)) cache(stairCount) else {
      val ways = staircase(stairCount - 1) + staircase(stairCount - 2) + staircase(stairCount - 3)
      cache.put(stairCount, ways)
      ways
    }
  }
}

object Solution extends App {
  new Staircase(new FileInputStream("src/main/resources/staircase.data"))
//  new Staircase(System.in)
}
