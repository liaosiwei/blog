package code.blog.snippet

import scala.xml.{Text, NodeSeq}
import code.blog.model.User
import net.liftweb.common.Full
import net.liftweb.http.DispatchSnippet

/**
 * User: sweeliao@gmail.com
 * Date: 14-1-16
 * Time: 下午7:45
 */
object HomePage extends DispatchSnippet{

  def dispatch: DispatchIt = {
    case "left" => handlerLeft _
    case "right" => handlerRight _
  }

  def handlerLeft (in: NodeSeq): NodeSeq = User.currentUser match {
    case Full(user) => {
      Text("TODO: add more details about the user")
    }
    case _ => in
  }

  def handlerRight (xhtml: NodeSeq): NodeSeq = User.currentUser match {
    case Full(user) => {
      user.posts match {
        case Nil => Text("You have no blog posted yet")
      }
    }
    case _ => <div data-lift="Menu.item?name=Login">Show unlogin Page</div>
  }
}
