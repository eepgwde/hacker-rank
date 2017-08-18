import scala.io.StdIn

/** Minimal memory solution to https://www.hackerrank.com/challenges/ctci-array-left-rotation */
object ArrayLeftRotation extends App {
  val numInts = StdIn.readInt   // number of integers to read
  val rotations = StdIn.readInt // number of rotations to apply

  val rotated: Seq[Int] = (0 to rotations).map(_ => StdIn.readInt) // save rotated integers for later

  (0 to numInts-rotations) foreach { _ => print(StdIn.readInt + " ") } // pass through non-rotated numbers
  rotated foreach { i => print(s"$i ") } // append rotated integers to remainder of stream
}
