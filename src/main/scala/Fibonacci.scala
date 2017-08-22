package fibonnaci

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.annotation.tailrec
import scala.collection.{immutable, mutable}

/** Solution to https://www.hackerrank.com/challenges/ctci-fibonacci-numbers
  *
  * To run:
  * {{ bin/run fibonnaci.Solution < src/main/resources/fibonnaci.data }} */
class Fibonacci(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val number: Int = in.nextInt()

  val defaultMap = immutable.HashMap(0 -> BigInt(0), 1 -> BigInt(1))
  val cache: mutable.Map[Int, BigInt] = mutable.WeakHashMap[Int, BigInt]().withDefault(defaultMap)

  println(fibMem(number))

  def fn(n: Int): BigInt = {
    @tailrec
    def fibIter(count: Int, fibN1: BigInt, fibN2: BigInt): BigInt =
      if (count == n) {
        cache += n -> fibN1
        fibN1
      }
      else fibIter(count + 1, fibN2, fibN1 + fibN2)

    fibIter(0, 0L, 1L)
  }

  def fibMem(n: Int): BigInt = cache.getOrElse(n, fn(n))
}

object Solution extends App {
//  new Fibonacci(new FileInputStream("src/main/resources/fibonacci.data"))
  new Fibonacci(System.in)
}
