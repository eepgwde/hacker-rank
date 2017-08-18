import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.io.StdIn

/** Version 1: Minimal memory solution to https://www.hackerrank.com/challenges/ctci-array-left-rotation
  * Requires one integer per line, which is not what the requirement states */
object ArrayLeftRotation1 extends App {
  val numInts = StdIn.readInt   // number of integers to read
  val rotations = StdIn.readInt // number of rotations to apply

  val rotated: Seq[Int] = (0 to rotations).map(_ => StdIn.readInt) // save rotated integers for later

  (0 to numInts-rotations) foreach { _ => print(StdIn.readInt + " ") } // pass through non-rotated numbers
  rotated foreach { i => print(s"$i ") } // append rotated integers to remainder of stream
}

/** Version 2: Expects data on two lines */
class ArrayLeftRotation2(in: InputStream) {
  val inputStreamReader = new InputStreamReader(in)
  val bufferedReader = new BufferedReader(inputStreamReader)
  val line1: String = bufferedReader.readLine.mkString
  val line2: String = bufferedReader.readLine.mkString

  val sc1: Scanner = new java.util.Scanner(line1)
  val numInts: Int = sc1.nextInt() // number of integers to read
  val rotations: Int = sc1.nextInt() // number of rotations to apply

  val sc2: Scanner = new java.util.Scanner(line2)
  val rotated: Vector[Int] = (1 to rotations).toVector map { _ => sc2.nextInt() } // save rotated integers for later

  (1 to numInts-rotations) foreach { _ => print(sc2.nextInt() + " ") } // pass through non-rotated numbers
  rotated foreach { i => print(s"$i ") }
}

object ArrayLeftRotation2 extends App {
  new ArrayLeftRotation2(new FileInputStream("src/main/resources/data"))
//  new ArrayLeftRotation2(System.in)
}
