package code.blog.snippet

import code.blog.model.User
import scala.xml.Text
import net.liftweb.common.Full
import net.liftweb.http.S
import net.liftweb.util.Helpers._

/**
 * User: sweeliao@gmail.com
 * Date: 14-2-20
 * Time: 下午9:39
 */
object WhoIs {
  def render = S.param("id") match{
    case Full(user) => {
      val profile = User.findById(user.toLong)
      profile match {
        case Nil =>   "#inner *" #> Text("暂无个人说明")
        case x => "#inner *" #> x.map(y => Text(y.textArea.get))
      }

    }
    case _ => Text("抱歉，查无此人!")
  }
}
