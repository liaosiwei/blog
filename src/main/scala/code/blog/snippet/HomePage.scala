package code.blog.snippet

import scala.xml.NodeSeq
import net.liftweb.util.Helpers._
import code.blog.model.{Post, User}
import net.liftweb.common.Full
import net.liftweb.http.DispatchSnippet
import net.liftweb.mapper.{Descending, OrderBy}
import net.liftweb.markdown.ActuariusTransformer
import scala.xml.XML.loadString

/**
 * User: sweeliao@gmail.com
 * Date: 14-1-16
 * Time: 下午7:45
 */
object HomePage extends DispatchSnippet{

  val parser = new ActuariusTransformer()

  def dispatch: DispatchIt = {
    case "left" => handlerLeft _
    case "right" => handlerRight _
  }

  def handlerLeft (in: NodeSeq): NodeSeq = User.currentUser match {
    case Full(user) => {
        <p>welcom, {user.shortName}</p> ++
          <div class="menu">
            <lift:Menu.builder />
          </div>
    }
    case _ => in
  }

  def handlerRight (in: NodeSeq): NodeSeq = {
    val postlength = 10
    val posts: List[Post] = Post.findAll(OrderBy(Post.dateOf, Descending)).take(postlength)
    ("#item *" #> posts.map(p => loadString("<div>" +
      parser(p.contents.get) + "</div>"))).apply(in)
  }
}
