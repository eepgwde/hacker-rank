import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}
import java.util

/** Solution to https://www.hackerrank.com/challenges/ctci-balanced-brackets */
class Brackets(in: InputStream) {
  val inputStreamReader = new InputStreamReader(in)
  val bufferedReader = new BufferedReader(inputStreamReader)
  val lineCount: Int = bufferedReader.readLine.toInt

  1 to lineCount foreach { _ =>
    val line: String = bufferedReader.readLine
    println(if (isBalanced(line)) "YES" else "NO")
  }

  def isEven(line: String): Boolean = (line.length % 1) == 0

  def isBalanced(line: String): Boolean = isEven(line) && {
    val s = new util.ArrayDeque[Char]
    val result = line forall {
        case '{' =>
          s.push('}')
          true

        case '(' =>
          s.push(')')
          true

        case '[' =>
          s.push(']')
          true

        case x =>
          if (s.isEmpty || x != s.peek) false else {
            s.pop
            true
          }
    }
    result && s.isEmpty
  }
}

object Brackets extends App {
  new Brackets(new FileInputStream("src/main/resources/brackets.data"))
//  new Brackets(System.in)
}
