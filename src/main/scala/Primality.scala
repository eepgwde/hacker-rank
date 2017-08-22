package primality

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner

/** Solution to https://www.hackerrank.com/challenges/ctci-big-o
  *
  * To run:
  * {{ bin/run primality.Solution < src/main/resources/primality.data }} */
class Primality(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val lineCount: Int = in.nextInt()

  1 to lineCount foreach { _ =>
    println(if (isPrime(in.nextInt())) "Prime" else "Not prime")
  }

  def isPrime(n: Int): Boolean = {
    if (n < 2) false else {
      val sqrt = Math.sqrt(n).toInt
      var i = 2
      while (i <= sqrt) {
        if (n % i == 0) return false
        i += 1
      }
      true
    }
  }
}

object Solution extends App {
  new Primality(new FileInputStream("src/main/resources/primality.data"))
//  new Primality(System.in)
}
