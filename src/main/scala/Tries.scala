import java.io.{FileInputStream, InputStream}
import java.util.Scanner
import scala.collection.mutable

/** Solution to https://www.hackerrank.com/challenges/ctci-contacts
  * Times out for 2 test cases, unclear why */
class Tries(inStream: InputStream) {
  val contacts: mutable.ArrayBuffer[String] = mutable.ArrayBuffer.empty[String]
  val trieRoot = new Trie()
  val results = new StringBuilder

  val in: Scanner = new Scanner(inStream)
  val lineCount: Int = in.nextInt()

  1 to lineCount foreach { _ =>
    val op: String = in.next()
    val name: String = in.next()
    op match {
      case "add" =>
        trieRoot.add(name)

      case _ =>
        results.append(trieRoot.find(name) + "\n")
    }
  }
  println(results)
}

class TrieNode {
  private val children = mutable.HashMap.empty[Char, TrieNode]
  var size = 0

  def put(ch: Char): TrieNode = {
    val trieNode = new TrieNode
    children.put(ch, trieNode)
    trieNode
  }

  def getChild(ch: Char): Option[TrieNode] = children.get(ch)
}

class Trie(words: Array[String] = Array.empty) {
  val root = new TrieNode
  words foreach add

  def add(str: String): Unit = {
    var curr: TrieNode = root
    str foreach { ch =>
      curr = curr.getChild(ch).getOrElse { curr.put(ch) }
      curr.size += 1
    }
  }

  def find(prefix: String): Int = {
    var curr = root
    /* Traverse tree to end of prefix */
    prefix foreach { ch =>
      curr = curr.getChild(ch).getOrElse(return 0)
    }
    curr.size
  }
}

object Tries extends App {
  new Tries(new FileInputStream("src/main/resources/tries.data"))
//  new Tries(System.in)
}
