class ReportMetrics(inputStream: java.io.InputStream) {
  import scala.collection.mutable

  protected val inputStreamReader = new java.io.InputStreamReader(inputStream)
  protected val bufferedReader = new java.io.BufferedReader(inputStreamReader)
  bufferedReader.readLine  // throw away first line in CSV file (the header)

  protected var colors: mutable.Set[String] = mutable.Set.empty

  protected val tallyEveryone   = new Tally
  protected val tallyAdultsOnly = new Tally

  protected val cvsReader = new WrappedCVSReader

  Iterator.continually { bufferedReader.readLine }
    .takeWhile(_ != -1)
    .takeWhile(x => x!=null && x.trim.nonEmpty)
    .map(cvsReader.parseCsv)
    .map { csv =>
      colors.add(csv.favoriteColor)
      csv
    }
    .map(tallyEveryone.tally)
    .filter(_.age > 21)  // this is odd, adults normally include age 21. Is the spec correct?
    .foreach(tallyAdultsOnly.tally)

  print(
    f"""${ tallyEveryone.userCount } user records were processed.
       |
       |${ tallyEveryone.summarize("For all users", colors.toSeq) }
       |${ tallyAdultsOnly.summarize("For adult users", colors.toSeq) }
       |""".stripMargin)
}

object Main extends App {
  new ReportMetrics(new java.io.FileInputStream("src/main/resources/users.csv"))
//  new ReportMetrics(System.in)
}
