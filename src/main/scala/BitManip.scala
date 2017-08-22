package bitManip

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/** Solution to https://www.hackerrank.com/challenges/ctci-lonely-integer
  *
  * To run:
  * {{
  * bin/run bitManip.Solution < src/main/resources/bitManip0.data
  * bin/run bitManip.Solution < src/main/resources/bitManip1.data
  * bin/run bitManip.Solution < src/main/resources/bitManip2.data
  * }} */
class BitManip(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val count: Int = in.nextInt()
  val numbers: mutable.ArrayBuffer[Int] = (1 to count).to[mutable.ArrayBuffer] map { _ => in.nextInt() }
  val result: ArrayBuffer[Int] = for {
    n <- numbers
    if numbers.count(_ == n)==1
  } yield n
  println(result.mkString(" "))
}

object Solution extends App {
//  new BitManip(new FileInputStream("src/main/resources/bitManip2.data"))
  new BitManip(System.in)
}
