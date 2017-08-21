package dfs

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.immutable

case class Cell(x: Int, y: Int, isFilled: Boolean, var wasVisited: Boolean=false) {
  lazy val neighbors: immutable.IndexedSeq[Cell] =
    for {
      row <- math.max(0, y-1) to math.min(DFS.lineCount-1,   y+1)
      col <- math.max(0, x-1) to math.min(DFS.columnCount-1, x+1)
      if row!=y || col!=x
    } yield DFS.cells(row)(col)

  lazy val neighborIndices: String =
    neighbors
      .map(n => s"[${ n.x }, ${ n.y }]")
      .mkString(", ")

  lazy val filledNeighbors: immutable.IndexedSeq[Cell] =
    if (isFilled) neighbors.filter(c => c.isFilled && !c.wasVisited) else immutable.IndexedSeq.empty[Cell]

  lazy val connectedNeighbors: immutable.IndexedSeq[Cell] = {
    wasVisited = true
    val fn = filledNeighbors
    //println(coordStr + ": " + fn.map(_.coordStr).mkString(", "))
    fn.filterNot(_.wasVisited).flatMap { cell =>
      cell.wasVisited = true
      cell.connectedNeighbors
    }.+:(this)
  }

  final def connectsTo(cell: Cell): Boolean =
    if (filledNeighbors.contains(cell)) true else
      filledNeighbors.exists(_.connectsTo(cell))

  lazy val filledNeighborCount: Int = if (isFilled) filledNeighbors.size else 0

  def coordStr: String = s"($x, $y)"
}

object DFS {
  //  val inStream: InputStream = new FileInputStream("src/main/resources/dfs.data")
  val inStream: InputStream = System.in
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val lineCount: Int = in.nextInt()
  val columnCount: Int = in.nextInt()

  // create cells
  val cells: IndexedSeq[IndexedSeq[Cell]] =
    (1 to lineCount).map { y =>
      (1 to columnCount).map { x =>
        Cell(x-1, y-1, in.nextInt()==1)
      }
    }

  def clearVisits(): Unit =
    (1 to lineCount).foreach { y =>
      (1 to columnCount).foreach { x =>
        cells(y-1)(x-1).wasVisited = false
      }
    }

  def showCounts(): Unit = {
    Console.err.println("Counts:")
    (0 until lineCount).foreach { y =>
      (0 until columnCount).foreach { x =>
        Console.err.print(cells(y)(x).filledNeighborCount + " ")
      }
      Console.err.println()
    }
    Console.err.println()
  }

  def showNeighbors(): Unit = {
    println("Neighbors:")
    (0 until lineCount).foreach { y =>
      (0 until columnCount).foreach { x =>
        val cell = cells(y)(x)
        Console.err.print(cell.coordStr + s": " + cell.neighborIndices + "\n")
      }
    }
    Console.err.println()
  }

  def apply(): Int = {
//    showNeighbors()
//    showCounts()
    DFS.clearVisits()
    val sizes = for {
      row <- cells.indices
      col <- cells(row).indices
    } yield cells(row)(col).connectedNeighbors.distinct.size
    sizes.max
  }
}

object Solution extends App {
  println(DFS())
}
