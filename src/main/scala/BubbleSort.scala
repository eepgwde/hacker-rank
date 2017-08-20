package bubbleSort

import java.io.{FileInputStream, InputStream}
import java.util.Scanner

/** Solution to https://www.hackerrank.com/challenges/ctci-bubble-sort
  *
  * To run:
  * {{ bin/run bubbleSort.Solution < src/main/resources/bubbleSort.data }} */
class BubbleSort(inStream: InputStream) {
  private var swaps = 0
  val in: Scanner = new Scanner(inStream)
  val size: Int = in.nextInt
  val array: Array[Int] = (0 until size).toArray.map { _ => in.nextInt }
  in.close()

  bubbleSort(array)
  println(s"Array is sorted in $swaps swaps.")
  println("First Element: " + array(0))
  println("Last Element: " + array(array.length - 1))

  private def bubbleSort(array: Array[Int]): Unit = {
    if (array.isEmpty) return

    var endOffset = 0
    var swapped = true
    while (swapped) {
      swapped = false
      1 until array.length - endOffset foreach { i =>
        if (array(i - 1) > array(i)) {
          swap(array, i - 1, i)
          swapped = true
        }
      }
      endOffset += 1
    }
  }

  /* Standard swap. Also updates the `swaps` variable */
  private def swap(array: Array[Int], i: Int, j: Int): Unit = {
    val temp = array(i)
    array(i) = array(j)
    array(j) = temp
    swaps += 1
  }
}

object Solution extends App {
  new BubbleSort(new FileInputStream("src/main/resources/bubbleSort.data"))
//  new BubbleSort(System.in)
}
