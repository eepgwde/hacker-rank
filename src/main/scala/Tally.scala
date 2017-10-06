import com.google.common.collect.TreeMultiset
import com.google.common.math.StatsAccumulator

class Tally {
  protected var _userCount: Long = 0L
  protected val colorMultiSet: TreeMultiset[String] = TreeMultiset.create()
  protected val stats: StatsAccumulator = new StatsAccumulator

  /** Display results */
  def summarize(heading: String, colors: Seq[String]): String =
    f"""$heading:
       |  Mean age: ${ stats.snapshot.mean }%.1f
       |  Median age: ${  }
       |  Top 5 favorite colors, and their respective counts: ${ formatColorCount(top5Colors(colors)) }
       |""".stripMargin

  /** Accumulate statistics */
  def tally: CSV => CSV =
    { csv =>
      colorMultiSet.add(csv.favoriteColor, 1)
      stats.add(csv.age.toDouble)
      _userCount = _userCount + 1
      csv
    }

  /** Getter */
  def userCount: Long = _userCount

  /** Helper for displaying top 5 colors in order, with occurrence totals */
  protected def formatColorCount(colors: Seq[(String, Int)]): String =
    colors
      .map { case (name, count) => s"$name ($count)" }
      .mkString(", ")

  /** Helper for obtaining the top 5 color names */
  protected def top5Colors(colorSet: Seq[String]): Seq[(String, Int)] =
    colorSet
      .map { color => (color, colorMultiSet.count(color)) }
      .sortBy(- _._2)
      .take(5)
}
