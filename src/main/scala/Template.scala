package template

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.mutable

/** Solution to https://www.hackerrank.com/challenges/
  *
  * To run:
  * {{ bin/run template.Solution < src/main/resources/template.data }} */
class Template(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val lineCount: Int = in.nextInt()

  1 to lineCount foreach { _ =>

  }
}

object Solution extends App {
  new Template(new FileInputStream("src/main/resources/template.data"))
//  new Template(System.in)
}
