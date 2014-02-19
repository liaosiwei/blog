package code.blog.snippet

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import code.blog.model.{Post, User}
import net.liftweb.common.{Box, Full}
import net.liftweb.http.DispatchSnippet
import net.liftweb.mapper.{Descending, OrderBy}
import net.liftweb.markdown.ActuariusTransformer
import scala.xml.XML.loadString
import Utils.slashDate

/**
 * User: sweeliao@gmail.com
 * Date: 14-1-16
 * Time: 下午7:45
 */
object HomePage/* extends DispatchSnippet*/{

  val parser = new ActuariusTransformer()

/*  def dispatch: DispatchIt = {
    case "renderBlog" => render _
  }*/

  def render = {
    val postlength = 10
    val posts: List[Post] = Post.findAll(OrderBy(Post.dateOf, Descending)).take(postlength)

    "#item" #> posts.map(p => loadString("<div>" + "<time>" +
      slashDate.format(p.dateOf.get) + "</time>" +
      parser(p.contents.get) + "</div>"))
  }
}
