import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import org.scalatest.Matchers._

import com.typesafe.scalalogging.Logger

// The singleton to test
import metrics.{ Disparity => impl }

@RunWith(classOf[JUnitRunner])
class TestDisparity extends FlatSpec {
  val logger = Logger(this.getClass.getName)

  "subsequence" should "return" in {

    /*
     * Complete the 'usernameDisparity' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY inputs as parameter.
     * ababa
     * ababaa
     */

    val inputs = Array("aa", "ababa")

    val s1 = inputs(0)
    logger.info(s"$s1 ${impl.usernameDisparity1(s1).toString}")

    val results = impl.usernameDisparity(inputs)

    val s = results.mkString(",")
    logger.info(inputs.mkString(",") + " - " + results.mkString(","))

  }
}
