package bfs

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.mutable
import Model._

/** Solution to https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach
  *
  * To run:
  * {{ bin/run bfs.Solution < src/main/resources/bfs.data }} */
object Model {
  val nodeIdQueue: mutable.Queue[Int] = mutable.Queue.empty
  var allNodes: Seq[Node] = Nil
  var edges: Edges = Edges()

  def nodeById(id: Int): Node = allNodes.filter(_.id==id).head

  def distanceTo(startNode: Node, destNode: Node): Unit =
    if (startNode==destNode) Hops(Some(0)) else {
      resetVisitedIds()
      nodeIdQueue.clear()
      nodeIdQueue.enqueue(startNode.id)
      while (nodeIdQueue.nonEmpty)
        distanceTo(destNode, 1)
    }

  protected def distanceTo(toNode: Node, hops: Int): Unit = {
    val fromNodeId = nodeIdQueue.dequeue()
    val fromNode: Node = nodeById(fromNodeId).copy(visited = true)
    if (fromNode.connectedNodeIds.isEmpty)
      toNode.hops = Hops(None)
    else if (fromNode.connectedNodeIds.contains(toNode.id))
      toNode.hops = Hops(Some(hops))
    else {
      val nextNodeIds: Seq[Int] =
        fromNode.connectedNodes
          .filterNot(_.visited)
          .map(_.id)
      if (nextNodeIds.nonEmpty) {
        nodeIdQueue.enqueue(nextNodeIds:_*)
        nextNodeIds.map(nodeById).foreach(_.visited=true)
      } else toNode.hops = Hops(None)
    }
  }

  protected def resetVisitedIds(): Unit = allNodes.foreach { _.visited = false }

  /** Return smallest Hop, or Hop(None) to indicate no connection */
  protected def minHop(hops: Seq[Hops]): Hops =
    if (hops.isEmpty) Hops(None) else {
      val definedHops: Seq[Hops] = hops.filter(_.value.isDefined)
      if (definedHops.isEmpty) Hops(None) else
        Hops(Some(definedHops.map(_.value.get).min))
    }
}

case class Hops(var value: Option[Int] = None) {
  def distance: Int = value.map(_ * 6).getOrElse(-1)

  def isDefined: Boolean = value.isDefined

  def increment: Hops = {
    value = value.map(_ + 1).orElse(Some(1))
    this
  }
}

/** @param id Int identifying this node
  * @param connectedNodeIds collection of Int referencing Nodes pointed to by this node
  * @param visited Boolean indicating if this node has been considered yet */
case class Node(id: Int, connectedNodeIds: Seq[Int], var visited: Boolean = false, var hops: Hops=Hops(None)) {
  lazy val connectedNodes: Seq[Node] = connectedNodeIds.map(Model.nodeById)
}

case class Edge(fromId: Int, toId: Int) {
  override def toString: String = s"Edge from $fromId to $toId"
}

case class Edges(value: IndexedSeq[Edge] = IndexedSeq.empty) {
  def connectsTo(nodeId: Int): Seq[Int] =
    (value.filter(_.fromId==nodeId).map(_.toId) ++ value.filter(_.toId==nodeId).map(_.fromId)).distinct

  override def toString: String = value.mkString("\n")
}

class BFS(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val queryCount: Int = in.nextInt()
  Console.err.println(s"queryCount=$queryCount")

  1 to queryCount foreach { q =>
    val (nodeCount, edgeCount) = (in.nextInt(), in.nextInt())
    Console.err.println(s"\nQuery $q: nodeCount=$nodeCount; edgeCount=$edgeCount")
    edges = Edges((1 to edgeCount).map(_ => Edge(in.nextInt(), in.nextInt())))
    Console.err.println(s"$edges")
    allNodes = (1 to nodeCount).map { i => Node.apply(i, edges.connectsTo(i)) }
    val startNode = nodeById(in.nextInt())
    Console.err.println(s"Starting from node #${ startNode.id }; ${ allNodes.size } nodes to visit")
    allNodes.foreach { distanceTo(startNode, _) }
    val distances: Seq[Int] = allNodes.filter(_ != startNode).map(_.hops.distance)
    println(distances.mkString(" "))
    Console.err.flush()
  }
}

object Solution extends App {
//  new BFS(new FileInputStream("src/main/resources/bfs.data"))
  new BFS(System.in)
}
