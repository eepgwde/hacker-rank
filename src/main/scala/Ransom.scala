package ransom

import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}

/** Solution to https://www.hackerrank.com/challenges/ctci-ransom-note
  *
  * To run:
  * {{
  * bin/run ransom.Solution < src/main/resources/ransom1.data
  * bin/run ransom.Solution < src/main/resources/ransom1.data
  * bin/run ransom.Solution < src/main/resources/ransom1.data
  * }} */
class Ransom(in: InputStream) {
  val inputStreamReader = new InputStreamReader(in)
  val bufferedReader    = new BufferedReader(inputStreamReader)

  val lineCounts: String   = bufferedReader.readLine
  val lineMagazine: String = bufferedReader.readLine
  val lineRansom: String   = bufferedReader.readLine

  val counts: Array[String] = lineCounts.split(" ")
  assert(counts.length==2)
  val magazineWordCount: Int = counts(0).toInt
  val ransomWordCount: Int   = counts(1).toInt

  val magazineWords: Seq[String] = lineMagazine.split(" ")
  val ransomWords: Seq[String]   = lineRansom.split(" ")
  assert(magazineWords.size==magazineWordCount)
  assert(ransomWords.size==ransomWordCount)

  val magConcord: Map[String, Int] = magazineWords.groupBy(identity).mapValues(_.size)
  val ranConcord: Map[String, Int] = ransomWords.groupBy(identity).mapValues(_.size)

  val canRansom: Boolean = ranConcord forall { case (word, count) =>
    count <= magConcord(word)
  }

  println(if (canRansom) "Yes" else "No")
}

object Solution extends App {
//  new Ransom(new FileInputStream("src/main/resources/ransom1.data"))
//  new Ransom(new FileInputStream("src/main/resources/ransom2.data"))
//  new Ransom(new FileInputStream("src/main/resources/ransom3.data"))
  new Ransom(System.in)
}
