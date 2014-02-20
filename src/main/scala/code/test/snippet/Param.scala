package code.test.snippet

import net.liftweb.util._
import Helpers._
import net.liftweb.common.Full
import net.liftweb.sitemap.Menu

/**
 * User: sweeliao@gmail.com
 * Date: 13-12-6
 * Time: 下午4:03
 */

// capture the page parameter information
case class ParamInfo(theParam: String)

// a snippet that takes the page parameter information
class ShowParam(pi: ParamInfo)  {
  def render = "*" #> pi.theParam
}

object Param {
  val menu = Menu.param[ParamInfo]("Param", "Param",
    s => Full(ParamInfo(s)),
    pi => pi.theParam) / "param"
  lazy val loc = menu.toLoc

  def render = "*" #> loc.currentValue.map(_.theParam)
}
