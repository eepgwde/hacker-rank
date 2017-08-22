package bfs

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.mutable

case class Nodes(nodes: IndexedSeq[Node]) {
  protected val visitedIds: mutable.HashSet[Int] = mutable.HashSet.empty

  def distancesFrom(startNode: Node): IndexedSeq[Int] = {
    resetVisitedIds()
    nodes
      .filterNot(_==startNode)
      .map { node => distanceBetween(startNode, node) }
  }

  def nodeById(id: Int): Node = nodes.filter(_.id==id).head

  protected def distanceBetween(fromNode: Node, toNode: Node): Int = {
    visitedIds.add(fromNode.id)
    val result = if (fromNode.connectedNodeIds.isEmpty)
      -1
    else if (fromNode.connectedNodeIds.contains(toNode.id))
      6
    else {
      val remainingConnectedIds: Seq[Int] = fromNode.connectedNodeIds diff visitedIds.toIndexedSeq
      val distances: Seq[Int] = remainingConnectedIds.map { connectedId =>
        Console.err.println(s"from ${ fromNode.id } to $connectedId")
        val connectedNode = nodeById(connectedId)
        val distance = distanceBetween(connectedNode, toNode)
        distance
      }
      val posDistances: Seq[Int] = distances.filter(_ > 0)
      if (posDistances.nonEmpty) posDistances.min else -1
    }
    result
  }

  protected def resetVisitedIds(): Unit = visitedIds.clear()
}

case class Node(id: Int, connectedNodeIds: Seq[Int])

case class Edges(value: Seq[(Int, Int)]) {
  def connectsTo(nodeId: Int): Seq[Int] =
    (value.filter(_._1==nodeId).map(_._2) ++ value.filter(_._2==nodeId).map(_._1)).distinct
}

class BFS(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val queryCount: Int = in.nextInt()

  1 to queryCount foreach { _ =>
    val nodeCount = in.nextInt()
    val edgeCount = in.nextInt()
    val edges: Edges = Edges((1 to edgeCount).map(_ => (in.nextInt(), in.nextInt())))
    val nodes: Nodes = Nodes((1 to nodeCount).map { i => Node.apply(i, edges.connectsTo(i)) })
    val startNode = nodes.nodeById(in.nextInt())
    val distances: Seq[Int] = nodes.distancesFrom(startNode)
    println(distances.mkString(" "))
  }
}

object Solution extends App {
//  new BFS(new FileInputStream("src/main/resources/bfs.data"))
  new BFS(System.in)
}
