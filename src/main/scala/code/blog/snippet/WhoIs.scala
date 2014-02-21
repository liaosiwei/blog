package code.blog.snippet

import code.blog.model.User
import scala.xml.{NodeSeq, Text}
import net.liftweb.common.Full
import net.liftweb.http.{StatefulSnippet, S}
import net.liftweb.util.Helpers._

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-20
 * Time: 下午9:39
 */
class WhoIs extends StatefulSnippet{

  private var userId: Long = 0

  def dispatch = {
    case "render" => render
    case "archive" => archive
  }

  def render(in: NodeSeq): NodeSeq = S.param("id") match{
    case Full(id) => {
      val profile = User.findById(id.toLong)
      profile match {
        case Nil =>   ("#inner *" #> Text("暂无个人说明")).apply(in)
        case x => {
          userId = id.toLong
          ("#name *" #> x.map(y => Text(y.shortName)) &
          "#intro *" #> x.map(y => Text({val txt = y.textArea.get; if (txt.isEmpty) "博主很懒，没留下太多。。。" else txt}))).apply(in)
        }
      }

    }
    case _ => Text("抱歉，查无此人!")
  }

  def archive(in: NodeSeq): NodeSeq = {
    ("#archive *" #> Text(userId.toString)).apply(in)
  }
}
