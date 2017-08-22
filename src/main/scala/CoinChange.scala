package change

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.mutable

/** Solution to https://www.hackerrank.com/challenges/ctci-coin-change
  * See https://www.topcoder.com/community/data-science/data-science-tutorials/dynamic-programming-from-novice-to-advanced/
  *
  * To run:
  * {{ bin/run change.Solution < src/main/resources/change.data }} */
class CoinChange(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val amount: Int = in.nextInt()
  val nCoins: Int = in.nextInt()
  val coins: Vector[Int] = (1 to nCoins).toVector map { _ => in.nextInt() }

  println(countChange(amount, coins))

  def countChange(amount: Int, coins: Vector[Int]): Long =
    if (amount < 0) 0
    else countChange(amount, coins, 0, mutable.HashMap.empty[String, Long])

  def countChange(amount: Int, coins: Vector[Int], coinNumber: Int, cache: mutable.HashMap[String, Long]): Long = {
      val key = amount + "," + coinNumber
      if (cache.contains(key)) {
        cache(key)
      } else {
        if (coinNumber == coins.size - 1) {
          if (amount % coins(coinNumber) == 0) {
            cache.put(key, 1L)
            1
          } else {
            cache.put(key, 0L)
            0
          }
        } else {
          val ways: Seq[Long] = 0 to amount by coins(coinNumber) map { i =>
            countChange(amount - i, coins, coinNumber + 1, cache)
          }
          cache.put(key, ways.sum)
          ways.sum
        }
      }
  }
}

object Solution extends App {
//  new CoinChange(new FileInputStream("src/main/resources/change.data"))
  new CoinChange(System.in)
}
