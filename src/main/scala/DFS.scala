package dfs

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner
import scala.collection.immutable

/** Solution to https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid
  *
  * To run:
  * {{ bin/run dfs.Solution < src/main/resources/dfs.data }} */
case class Cell(x: Int, y: Int, isFilled: Boolean, var wasVisited: Boolean=false) {
  lazy val coordStr: String = s"($x, $y)"

  lazy val neighbors: immutable.IndexedSeq[Cell] =
    for {
      row <- math.max(0, y-1) to math.min(DFS.lineCount-1,   y+1)
      col <- math.max(0, x-1) to math.min(DFS.columnCount-1, x+1)
      if row!=y || col!=x
    } yield DFS.cells(row)(col)

  lazy val neighborIndices: String =
    neighbors
      .map(_.coordStr)
      .mkString(", ")

  lazy val filledNeighbors: immutable.IndexedSeq[Cell] =
    if (isFilled) neighbors.filter(c => c.isFilled && !c.wasVisited) else immutable.IndexedSeq.empty[Cell]

  lazy val filledNeighborCount: Int = if (isFilled) filledNeighbors.size else 0

  lazy val connectedNeighbors: immutable.IndexedSeq[Cell] = {
    wasVisited = true
    val fn = filledNeighbors.filterNot(_.wasVisited)
    //println(coordStr + ": " + fn.map(_.coordStr).mkString(", "))
    fn.flatMap { cell =>
      cell.wasVisited = true
      cell.connectedNeighbors
    }.+:(this)
     .distinct
  }

  final def connectsTo(cell: Cell): Boolean =
    if (filledNeighbors.contains(cell)) true else
      filledNeighbors.exists(_.connectsTo(cell))
}

object DFS {
  val inStream: InputStream = System.in
//  val inStream: InputStream = new FileInputStream("src/main/resources/dfs.data")

  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)

  val lineCount: Int = in.nextInt()
  val columnCount: Int = in.nextInt()
  val cells: IndexedSeq[IndexedSeq[Cell]] = createCells(lineCount, columnCount)

  def apply(): Int = {
//    showNeighbors()
//    showCounts()
    DFS.clearVisits()
    val sizes = for {
      row <- cells.indices
      col <- cells(row).indices
    } yield cells(row)(col).connectedNeighbors.size
    sizes.max
  }

  def clearVisits(): Unit =
    cells.indices.foreach { y =>
      cells(y).indices.foreach { x =>
        cells(y)(x).wasVisited = false
      }
    }

  def createCells(nLines: Int, nColumns: Int): IndexedSeq[IndexedSeq[Cell]] =
    (0 until nLines).map { y =>
      (0 until nColumns).map { x =>
        Cell(x, y, in.nextInt()==1)
      }
    }

  def showCounts(): Unit = {
    Console.err.println("Counts:")
    cells.indices.foreach { y =>
      cells(y).indices.foreach { x =>
        Console.err.print(cells(y)(x).filledNeighborCount + " ")
      }
      Console.err.println()
    }
    Console.err.println()
  }

  def showNeighbors(): Unit = {
    println("Neighbors:")
    cells.indices.foreach { y =>
      cells(y).indices.foreach { x =>
        val cell = cells(y)(x)
        Console.err.print(cell.coordStr + s": " + cell.neighborIndices + "\n")
      }
    }
    Console.err.println()
  }
}

object Solution extends App {
  println(DFS())
}
