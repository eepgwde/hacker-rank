import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner

class MergeSorter {
  /* The array to be sort can have up to n = 100,000 elements, so there may be O(n^2) swaps.
    n^2 is 10,000,000,000. An Int has a maximum value 2,147,483,647 so a Long is used
    to avoid integer overflow */
  private var swaps: Long = 0L

  def mergeSort(array: Array[Int]): Long = {
    val helper = new Array[Int](array.length)
    mergeSort(array, helper, 0, array.length - 1)
    swaps
  }

  private def mergeSort(array: Array[Int], helper: Array[Int], start: Int, end: Int): Unit = {
    if (start < end) {
      val mid = (start + end) / 2
      mergeSort(array, helper, start, mid)
      mergeSort(array, helper, mid + 1, end)
      merge(array, helper, start, mid, end)
    }
  }

  private def merge(array: Array[Int], helper: Array[Int], start: Int, mid: Int, end: Int): Unit = {
    /* Fill helper array with same elements as original array */
    start to end foreach { i =>
      helper(i) = array(i)
    }
    var curr = start
    var left = start
    var right = mid + 1

    /* Loop through helper's left and right halves and copy the smaller element to array */
    while (left <= mid && right <= end)
      if (helper(left) <= helper(right)) {
        array(curr) = helper(left)
        curr += 1
        left += 1
      } else {
        /* Each time an element is chosen from the right side, count how many elements
          the left side has compared to the left side. This is equivalent to counting swaps. */
        swaps += mid + 1 - left
        array(curr) = helper(right)
        curr += 1
        right += 1
      }
    /* Copy remaining elements of left half. Right half elements are already in place */
    while (left <= mid) {
      array(curr) = helper(left)
      curr += 1
      left += 1
    }
  }
}

class MergeSort(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in = new Scanner(inStream)
  val testCases: Int = in.nextInt()
  1 to testCases foreach { _ =>
    val itemCount = in.nextInt()
    val array: Array[Int] = (0 until itemCount).toArray.map { _ => in.nextInt() }
    println(new MergeSorter().mergeSort(array))
  }
}

object MergeSort extends App {
  new MergeSort(new FileInputStream("src/main/resources/mergeSort.data"))
//  new MergeSort(System.in)
}
