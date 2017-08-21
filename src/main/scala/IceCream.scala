package iceCream

import java.io.{FileInputStream, InputStream, InputStreamReader}
import java.util.Scanner

class IceCream(inStream: InputStream) {
  val inputStreamReader = new InputStreamReader(inStream)
  val in: Scanner = new Scanner(inStream)
  val lineCount: Int = in.nextInt()

  1 to lineCount foreach { _ =>
    val money: Int = in.nextInt()
    val n: Int = in.nextInt()
    val costs: Seq[Int] = (1 to n).map { _ => in.nextInt() }
    println(solution(money, costs).mkString(" "))
  }

  def solution(money: Int, costs: Seq[Int]): Seq[Int] = {
    costs.zipWithIndex.foreach { case (sunnyCost, sunnyIndex) =>
      costs.zipWithIndex.foreach { case (johnnyCost, johnnyIndex) =>
        if (sunnyCost + johnnyCost == money && sunnyIndex!=johnnyIndex)
          return List(sunnyIndex+1, johnnyIndex+1).sorted
      }
    }
    throw new Exception("No solution found")
  }
}

object Solution extends App {
  new IceCream(new FileInputStream("src/main/resources/iceCream.data"))
//  new IceCream(System.in)
}
