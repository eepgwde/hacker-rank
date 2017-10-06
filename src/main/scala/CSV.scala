/** Contains result of parsing one line of the CSV file */
case class CSV(userId: Long, age: Int, favoriteColor: String) {
  override def toString: String = s"CSV($userId, $age, $favoriteColor)"
}

object WrappedCVSReader {
  // consolidate alternate spellings of the same color
  protected val colorNameAliases = Map("gray" -> "grey")

  def normalizeColorNames(name: String): String = {
    val lcName = name.toLowerCase()
    colorNameAliases.getOrElse(lcName, lcName)
  }
}

class WrappedCVSReader {
  import purecsv.unsafe.CSVReader

  protected val csvReader: CSVReader[CSV] = CSVReader[CSV]

  val parseCsv: String => CSV =
    (line: String) =>
      csvReader
        .readCSVFromString(line)
        .map { csv => csv.copy(favoriteColor = WrappedCVSReader.normalizeColorNames(csv.favoriteColor)) }
        .head
}
