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

/*
class WhoIs{

  private var userId: Long = 0

  def dispatch = {
    case "render" => render
    case "archive" => archive
  }



  def archive(in: NodeSeq): NodeSeq = {
    ("#archive *" #> Text(userId.toString)).apply(in)
  }
}
*/
