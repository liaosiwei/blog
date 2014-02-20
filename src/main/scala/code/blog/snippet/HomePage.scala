package code.blog.snippet

import net.liftweb.util.Helpers._
import code.blog.model.Post
import net.liftweb.mapper.{Descending, OrderBy}
import net.liftweb.markdown.ActuariusTransformer
import scala.xml.XML.loadString
import Utils.slashDate
import scala.xml.{Text, NodeSeq}

/**
 * User: sweeliao@gmail.com
 * Date: 14-1-16
 * Time: 下午7:45
 */
object HomePage/* extends DispatchSnippet*/{

  val parser = new ActuariusTransformer()

  def getHtml(markdown: String): NodeSeq = {
    val mk = parser(markdown)
    loadString("<div>" + mk + "</div>")
  }

/*  def dispatch: DispatchIt = {
    case "renderBlog" => render _
  }*/

  def render = {
    val postlength = 10
    val posts: List[Post] = Post.findAll(OrderBy(Post.dateOf, Descending)).take(postlength)

    "#item *" #> posts.map(p => {
      ".sepline *" #> <time>{slashDate.format(p.dateOf.get)}</time> &
        "#post" #> getHtml(p.contents.get) &
        "#author *" #> <p>Posted By {p.owner.obj.map(_.shortName) openOr Text("no author recording")}</p>
      }
    )
  }
}
