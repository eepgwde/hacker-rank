import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import org.scalatest.Matchers._

import com.typesafe.scalalogging.Logger

import fibonacci.Fibonacci

@RunWith(classOf[JUnitRunner])
class TestFibonacci extends FlatSpec {
  val logger = Logger(this.getClass.getName)

  "Fibonacci" should "return Fibonacci numbers" in {
    val fib = new Fibonacci()

    val nfib = fib.fib(3)
    logger.info(s"3 has $nfib")
    assert(nfib === 2)
  }

  "subsequence" should "return sequence" in {

    /*
     * Complete the 'usernameDisparity' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY inputs as parameter.
     * ababa
     * ababaa
     */

    def similarity0(pfx: String, str: String): Int = {
      if (pfx.isEmpty) return str.length
      if (pfx == str) return pfx.length

      if (! str.startsWith(pfx)) return 0
      return str.length
    }

    def similarity1(x: (String, String)): Int = similarity0(x._1, x._2)

    def split(idx: Int, str:String): (String, String) =
      (str.substring(0, idx), str.substring(idx,str.length))

    def u0(input: String) : List[((String, String), Int)] = {
      val v = List.range(0, input.length).map(i => split(i, input))
      val s = v.map(similarity1)
      return v zip s
    }

    def usernameDisparity1(input: String): Int =
      List.range(0, input.length).map(i => split(i, input)).map(similarity1).sum

    def usernameDisparity(inputs: Array[String]): Array[Int] =
      inputs.map(x => usernameDisparity1(x)).toArray

    val inputs = Array("aa", "ababa")

    val s1 = inputs(0)
    logger.info(s"$s1 ${usernameDisparity1(s1).toString}")

    val results = usernameDisparity(inputs)


    val s = results.mkString(",")
    logger.info(inputs.mkString(",") + " - " + results.mkString(","))

  }
}
