package code.blog.snippet

import net.liftweb.sitemap.Menu
import net.liftweb.common.Full

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-20
 * Time: 下午5:30
 */

case class PostId(theParam: String)

object ShowPost {
  val menu = Menu.param[PostId]("Param", "Param", s => Full(PostId(s)), pi => pi.theParam) / "param"
  lazy val loc = menu.toLoc
  ​
  def render = "*" #> loc.currentValue.map(_.theParam)
}
