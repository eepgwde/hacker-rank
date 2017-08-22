package change

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner

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
  val coins: List[Int] = (1 to nCoins).toList map { _ => in.nextInt() }
  val countChange: ((Int, List[Int])) => Int =
    memoize((_countChange _).tupled)

  println(countChange((amount, coins)))

  def _countChange(amount: Int, coins: List[Int]): Int = {
    if (amount < 0)
      0
    else if (coins.isEmpty)
      if (amount == 0) 1 else 0
    else
      countChange((amount, coins.tail)) + countChange((amount - coins.head, coins))
  }

  def memoize[Key, Value](f: Key => Value): (Key) => Value = {
    val cache = collection.mutable.WeakHashMap.empty[Key, Value]
    (key: Key) => cache.getOrElseUpdate(key, f(key))
  }
}

object Solution extends App {
  new CoinChange(new FileInputStream("src/main/resources/change.data"))
//  new CoinChange(System.in)
}
