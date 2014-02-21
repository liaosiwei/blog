package code.blog.snippet

import net.liftweb.util.Helpers._
import code.blog.model.Post
import scala.xml.Text

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-21
 * Time: 上午10:05
 */
object BlogAchive {

  def getName(post: Post): String = Post.name.get.split("\\.")(0)

  def render = {
    "#archive *" #> Text("")
  }
}
