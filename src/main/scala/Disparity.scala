/***
 * @file Disparity.scala
 * @author weaves
 * @brief Coding example: string disparity
 *
 * I came across this example. It's related to the longest prefix of a suffix.
 *
 * Given a string ababa, if we split it to "a" and "baba", then the similarity score
 * is 0. But "ab", "aba" has a similarity score of 3, "aba" "ba" of 0
 * "abab" "a" of 1. Also "" and "ababa" has a score of 5.
 *
 * So the total score is 3 + 1 + 5 or 9.
 *
 * This isn't strictly correct. I've been told that "ababa" has a score or 10
 * That "aa" has a score of 3. "ababaa" a score of 11.
 *
 * I've coded it up for what it's worth.
 */
package metrics

object Disparity {

  def compare0(a: String, b: String, n: Int): Int =
    if (b.startsWith(a)) n else 0

  def similarity0(pfx: String, str: String): Int = {
    if (pfx.isEmpty) return str.length
    if (pfx == str) return pfx.length

    if (pfx.length < str.length)
      return compare0(pfx, str, pfx.length)

    if (pfx.length > str.length)
      return compare0(str, pfx, str.length)

    return 0
  }

  def similarity1(x: (String, String)): Int = similarity0(x._1, x._2)

  def split(idx: Int, str:String): (String, String) =
    (str.substring(0, idx), str.substring(idx,str.length))

  def u0(input: String) : List[((String, String), Int)] = {
    val v = List.range(0, input.length).map(i => split(i, input))
    val s = v.map(similarity1)
    return v zip s
  }

  def usernameDisparity1(input: String): Int =
    List.range(0, input.length).map(i => split(i, input)).map(similarity1).sum

  def usernameDisparity(inputs: Array[String]): Array[Int] =
    inputs.map(x => usernameDisparity1(x)).toArray

}
