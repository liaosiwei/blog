package code.blog.snippet

import net.liftweb.util.Helpers._

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-21
 * Time: 上午10:43
 */
object CustomMenu {
  def render = "href=/archive [href]" #> "/archive?id=0"
}
