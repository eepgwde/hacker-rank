import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}

/** Solution to https://www.hackerrank.com/challenges/ctci-making-anagrams */
class Anagrams(in: InputStream) {
  val inputStreamReader = new InputStreamReader(in)
  val bufferedReader = new BufferedReader(inputStreamReader)
  val lineA: String = bufferedReader.readLine.mkString
  val lineB: String = bufferedReader.readLine.mkString

  def occurrancesInA(c: Char): Int = lineA.count(_ == c)
  def occurrancesInB(c: Char): Int = lineB.count(_ == c)

  var charsToRemove = 0

  (lineA+lineB).distinct foreach { c =>
    val aCount: Int = occurrancesInA(c)
    val bCount: Int = occurrancesInB(c)
    val minCount: Int = math.min(aCount, bCount)
    if (aCount>minCount)
      charsToRemove = charsToRemove + aCount-minCount
    if (bCount>minCount)
      charsToRemove = charsToRemove + bCount-minCount
  }

  print(charsToRemove)
}

object Anagrams extends App {
  new Anagrams(new FileInputStream("src/main/resources/anagram.data"))
  //new Anagrams(System.in)
}