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

  val staircase: Int => Int = memoize(_staircase)

  1 to staircaseCount foreach { _ =>
    println(staircase(in.nextInt()))
  }

  def _staircase(stairCount: Int): Int = {
    if (stairCount < 0) 0 else
    if (stairCount==0) 1 else
      staircase(stairCount - 1) + staircase(stairCount - 2) + staircase(stairCount - 3)
  }

  def memoize[Key, Value](f: Key => Value): (Key) => Value = {
    val cache = collection.mutable.WeakHashMap.empty[Key, Value]
    (key: Key) => {
      cache.getOrElseUpdate(key, f(key))
    }
  }
}

object Solution extends App {
  new Staircase(new FileInputStream("src/main/resources/staircase.data"))
//  new Staircase(System.in)
}
