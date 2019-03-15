package fibonacci

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.annotation.tailrec
import scala.collection.{immutable, mutable}

/** Solution to https://www.hackerrank.com/challenges/ctci-fibonacci-numbers
  *
  * To run:
  * {{ bin/run fibonnaci.Solution < src/main/resources/fibonnaci.data }} */
class Fibonacci {
  val defaultMap = immutable.HashMap(0 -> BigInt(0), 1 -> BigInt(1))
  val cache: mutable.Map[Int, BigInt] =
    mutable.WeakHashMap[Int, BigInt]().withDefault(defaultMap)

  def fib0(n: Int): BigInt = {
    @tailrec
    def fibIter(count: Int, fibN1: BigInt, fibN2: BigInt): BigInt =
      if (count == n) {
        cache += n -> fibN1
        fibN1
      }
      else fibIter(count + 1, fibN2, fibN1 + fibN2)

    fibIter(0, 0L, 1L)
  }

  def fib(n: Int): BigInt = cache.getOrElse(n, fib0(n))
}

object Solution extends App {
  val fib = new Fibonacci()

  val inStream = new FileInputStream("src/main/resources/fibonacci.data")
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val number: Int = in.nextInt()

  val nfib = fib.fib(number)

  println(s"$number has $nfib")
}
