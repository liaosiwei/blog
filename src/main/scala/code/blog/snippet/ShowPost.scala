package code.blog.snippet

import net.liftweb.sitemap.Menu
import net.liftweb.common.{Empty, Full}
import net.liftweb.util.Helpers._
import net.liftweb.http.S
import scala.xml.Text
import code.blog.model.Post
import net.liftweb.mapper.By

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-20
 * Time: 下午5:30
 */

object ShowPost{
  def render = S.param("id") match {
    case Full(id) => {
      val post = Post.findById(id.toLong)
      post match {
        case Nil => "#item *" #> Text("没有这篇博客！")
        case x => HomePage.renderBlog(x)
      }
    }
    case _ => "*" #> Text("访问出错")
  }
}