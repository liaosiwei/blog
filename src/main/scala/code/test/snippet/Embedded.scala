package code.test.snippet

import net.liftweb.http.S
import net.liftweb.util._
import Helpers._

/**
 * User: sweeliao@gmail.com
 * Date: 13-12-6
 * Time: 下午3:55
 */
object Embedded {
  def from = "*" #> S.location.map(_.name)
}
